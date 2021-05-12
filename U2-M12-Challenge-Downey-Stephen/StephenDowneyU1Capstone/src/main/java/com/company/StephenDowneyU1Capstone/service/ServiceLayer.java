package com.company.StephenDowneyU1Capstone.service;

import com.company.StephenDowneyU1Capstone.daos.*;
import com.company.StephenDowneyU1Capstone.exceptions.OutOfStockException;
import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.*;
import com.company.StephenDowneyU1Capstone.viewModels.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ServiceLayer{

    private ConsoleDao consoleDao;
    private GameDao gameDao;
    private InvoiceDao invoiceDao;
    private ProcessingFeeDao processingFeeDao;
    private SalesTaxDao salesTaxDao;
    private TShirtDao tShirtDao;

    @Autowired
    public ServiceLayer(ConsoleDao consoleDao, GameDao gameDao, InvoiceDao invoiceDao, ProcessingFeeDao processingFeeDao,
                        SalesTaxDao salesTaxDao, TShirtDao tShirtDao){
        this.consoleDao = consoleDao;
        this.gameDao = gameDao;
        this.invoiceDao = invoiceDao;
        this.processingFeeDao = processingFeeDao;
        this.salesTaxDao = salesTaxDao;
        this.tShirtDao = tShirtDao;
    }

    @Transactional
    public Console addConsole(Console console){
        return consoleDao.saveConsole(console);
    }

    public Console getConsole(int consoleId) throws JdbcOperationFailedException {
        return consoleDao.findConsole(consoleId);
    }

    public List<Console> getConsoleByManufacturer(String manufacturer){
        return consoleDao.findConsoleByManufacturer(manufacturer);
    }

    public List<Console> getAllConsoles(){
        return consoleDao.findAllConsoles();
    }

    @Transactional
    public void updateConsole(Console console) throws JdbcOperationFailedException{
        consoleDao.updateConsole(console);
    }

    @Transactional
    public void deleteConsole(int consoleId) throws JdbcOperationFailedException{
        consoleDao.deleteConsole(consoleId);
    }

    @Transactional
    public Game addGame(Game game){
        return gameDao.saveGame(game);
    }

    public Game getGame(int gameId) throws JdbcOperationFailedException {
        return gameDao.findGame(gameId);
    }

    public List<Game> getGameByStudio(String studio){
        return gameDao.findGameByStudio(studio);
    }

    public List<Game> getGameByEsrbRating(String esrbRating){
        return gameDao.findGameByEsrbRating(esrbRating);
    }

    public List<Game> getGameByTitle(String title){
        return gameDao.findGameByTitle(title);
    }

    public List<Game> getAllGames(){
        return gameDao.findAllGames();
    }

    @Transactional
    public void updateGame(Game game) throws JdbcOperationFailedException{
        gameDao.updateGame(game);
    }

    @Transactional
    public void deleteGame(int gameId) throws JdbcOperationFailedException{
        gameDao.deleteGame(gameId);
    }

    @Transactional
    public TShirt addTShirt(TShirt tShirt){
        return tShirtDao.saveTShirt(tShirt);
    }

    public TShirt getTShirt(int tShirtId) throws JdbcOperationFailedException {
        return tShirtDao.findTShirt(tShirtId);
    }

    public List<TShirt> getTShirtByColor(String color){
        return tShirtDao.findTShirtByColor(color);
    }

    public List<TShirt> getTShirtBySize(String size){
        return tShirtDao.findTShirtBySize(size);
    }

    public List<TShirt> getAllTShirt(){
        return tShirtDao.findAllTShirts();
    }

    @Transactional
    public void updateTShirt(TShirt tShirt) throws JdbcOperationFailedException{
        tShirtDao.updateTShirt(tShirt);
    }

    @Transactional
    public void deleteTShirt(int tShirtId) throws JdbcOperationFailedException{
        tShirtDao.deleteTShirt(tShirtId);
    }

    @Transactional
    public InvoiceViewModel placeOrderAndAddInvoice(Invoice invoice) throws OutOfStockException, JdbcOperationFailedException {

        Item lineItem = getItem(invoice.getItemType(), invoice.getItemId(), invoice.getQuantity());
        BigDecimal processingFee = getProcessingFeeAmount(invoice.getItemType(), invoice.getQuantity());

        BigDecimal subtotal = getSubTotal(lineItem.getPrice(), invoice.getQuantity());
        BigDecimal taxRate = getSalesTaxRate(invoice.getState());
        BigDecimal tax = subtotal.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(tax.add(processingFee));

        invoice.setUnitPrice(lineItem.getPrice());

        invoice.setSubtotal(subtotal);
        invoice.setTax(tax);
        invoice.setProcessingFee(processingFee);
        invoice.setTotal(total);
        invoice = invoiceDao.saveInvoice(invoice);

        return assembleInvoiceViewModel(invoice, lineItem, taxRate);
    }

    private Item getItem(String itemType, Integer itemId, Integer quantity) throws  OutOfStockException, JdbcOperationFailedException {
        Item returnVal;
        switch (itemType){
            case("Games"):
                Game game = gameDao.findGame(itemId);

                if(quantity > game.getQuantity()){
                    throw new OutOfStockException("Unable to purchase " + quantity + " " + game.getTitle() + "s. Only " + game.getQuantity() + " left in stock.");
                }
                game = reduceGameStock(game, quantity);

                returnVal = game;
                break;
            case("TShirts"):
                TShirt tShirt = tShirtDao.findTShirt(itemId);;

                if(quantity > tShirt.getQuantity()){
                    throw new OutOfStockException("Unable to purchase " + quantity + " " + tShirt.getDescription() + "s. Only " + tShirt.getQuantity() + " left in stock.");
                }
                tShirt = reduceTShirtStock(tShirt, quantity);

                returnVal = tShirt;
                break;
            case("Consoles"):
                Console console = consoleDao.findConsole(itemId);

                if(quantity > console.getQuantity()){
                    throw new OutOfStockException("Unable to purchase " + quantity + " " + console.getModel() + "s. Only " + console.getQuantity() + " left in stock.");
                }
                console = reduceConsoleStock(console, quantity);

                returnVal = console;
                break;
            default:
                //I handle the issue of invalid itemType at the repo level. This will return a null Item, but the item isn't used before the program
                //calls the dao for the processing fee. If an invalid itemType was entered, then it will be caught there.
                return null;
        }
        return returnVal;
    }

    private BigDecimal getSubTotal(BigDecimal price, Integer quantity){
        return price.multiply(new BigDecimal(quantity));
    }

    private BigDecimal getSalesTaxRate(String state) throws JdbcOperationFailedException {
        SalesTaxRate salesTaxRate = salesTaxDao.findSalesTaxByState(state);
        return salesTaxRate.getRate();
    }

    private BigDecimal getProcessingFeeAmount(String itemType, Integer quantity) throws JdbcOperationFailedException {
        ProcessingFee processingFee = processingFeeDao.findProcessingFeeByItemType(itemType);
        BigDecimal fee = processingFee.getFee();
        if(quantity > 10){
            fee = fee.add(new BigDecimal("15.49"));
        }
        return  fee;
    }

    private InvoiceViewModel assembleInvoiceViewModel(Invoice invoice, Item item, BigDecimal taxRate){
        InvoiceViewModel returnVal = new InvoiceViewModel();
        returnVal.setInvoiceId(invoice.getInvoiceId());
        returnVal.setName(invoice.getName());
        returnVal.setStreet(invoice.getStreet());
        returnVal.setCity(invoice.getCity());
        returnVal.setState(invoice.getState());
        returnVal.setZipcode(invoice.getZipcode());
        returnVal.setItem(item);
        returnVal.setSubtotal(invoice.getSubtotal());
        returnVal.setSalesTaxRate(taxRate);
        returnVal.setTax(invoice.getTax());
        returnVal.setProcessingFee(invoice.getProcessingFee());
        returnVal.setTotal(invoice.getTotal());
        return returnVal;
    }

    private Game reduceGameStock(Game game, Integer invoiceQuantity) throws JdbcOperationFailedException{
        Integer startingInventoryLevel = game.getQuantity();
        Integer updatedInventoryLevel = startingInventoryLevel - invoiceQuantity;
        game.setQuantity(updatedInventoryLevel);
        gameDao.updateGame(game);
        //setting the Item.quantity to the invoice quantity, as to not expose internal inventories
        //In order to not duplicate data, the InvoiceViewModel won't have a quantity property like Invoice, but that value is still accessible through
        //the Invoices Item property
        game.setQuantity(invoiceQuantity);
        return game;
    }

    private TShirt reduceTShirtStock(TShirt tShirt, Integer invoiceQuantity) throws JdbcOperationFailedException{
        Integer startingInventoryLevel = tShirt.getQuantity();
        Integer updatedInventoryLevel = startingInventoryLevel - invoiceQuantity;
        tShirt.setQuantity(updatedInventoryLevel);
        tShirtDao.updateTShirt(tShirt);
        tShirt.setQuantity(invoiceQuantity);
        return tShirt;
    }

    private Console reduceConsoleStock(Console console, Integer invoiceQuantity) throws JdbcOperationFailedException{
        Integer startingInventoryLevel = console.getQuantity();
        Integer updatedInventoryLevel = startingInventoryLevel - invoiceQuantity;
        console.setQuantity(updatedInventoryLevel);
        consoleDao.updateConsole(console);
        console.setQuantity(invoiceQuantity);
        return console;
    }
}
