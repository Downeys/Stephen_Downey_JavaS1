package com.company;

import java.util.Objects;

public abstract class playerClass {
    protected String name;
    protected int strength;
    protected int health;
    protected int stamina;
    protected int speed;
    protected int attackPower;

    public int attack(){
        int damage = 0; //TODO
        return damage;
    }

    public int heal(){
        int amountToHeal = 0; //TODO
        health += amountToHeal;
        return health;
    }

    public int heal(int amountToHeal){
        health += amountToHeal;
        return health;
    }

    public int decreaseHealth(int amountToDecreaseHealth){
        health -= amountToDecreaseHealth;
        return health;
    }

    public int increaseStamina(){
        int amountToIncreaseStamina = 0; //TODO
        stamina += amountToIncreaseStamina;
        return stamina;
    }

    public int increaseStamina(int amountToIncreaseStamina){
        stamina += amountToIncreaseStamina;
        return stamina;
    }

    public int decreaseStamina(){
        int amountToDecreaseStamina = 0; //TODO
        stamina += amountToDecreaseStamina;
        return stamina;
    }

    public int decreaseStamina(int amountToDecreaseStamina){
        stamina += amountToDecreaseStamina;
        return stamina;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        playerClass that = (playerClass) o;
        return strength == that.strength && health == that.health && stamina == that.stamina && speed == that.speed && attackPower == that.attackPower && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, strength, health, stamina, speed, attackPower);
    }

    @Override
    public String toString() {
        return "playerClass{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                ", health=" + health +
                ", stamina=" + stamina +
                ", speed=" + speed +
                ", attackPower=" + attackPower +
                '}';
    }
}
