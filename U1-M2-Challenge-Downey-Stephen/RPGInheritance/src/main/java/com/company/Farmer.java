package com.company;

public class Farmer extends playerClass{

    public Farmer(){
        this.setName("Farmer");
        this.setStrength(75);
        this.setHealth(100);
        this.setStamina(75);
        this.setSpeed(20);
        this.setAttackPower(1);
    };

    public Farmer(String name){
        this.setName(name);
        this.setStrength(75);
        this.setHealth(100);
        this.setStamina(75);
        this.setSpeed(20);
        this.setAttackPower(1);
    }

    public void plow(){
        System.out.println(this.getName() + " starts plowing.");
    }

    public void harvest(){
        System.out.println(this.getName() + " starts harvesting.");
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                ", health=" + health +
                ", stamina=" + stamina +
                ", speed=" + speed +
                ", attackPower=" + attackPower +
                '}';
    }
}
