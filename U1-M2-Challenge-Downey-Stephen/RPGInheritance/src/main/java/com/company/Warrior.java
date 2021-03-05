package com.company;

import java.util.Objects;

public class Warrior extends playerClass {
    private int shieldStrength;

    public Warrior(){
        this.setName("Warrior");
        this.setStrength(75);
        this.setHealth(100);
        this.setStamina(100);
        this.setSpeed(50);
        this.setAttackPower(10);
        shieldStrength = 100;
    };

    public Warrior(String name){
        this.setName(name);
        this.setStrength(75);
        this.setHealth(100);
        this.setStamina(100);
        this.setSpeed(50);
        this.setAttackPower(10);
        shieldStrength = 100;
    }

    public int decreaseShieldStrength(){
        int amountToDecreaseShield = 1;
        shieldStrength -= shieldStrength - amountToDecreaseShield;
        return shieldStrength;
    }

    public int decreaseShieldStrength(int amountToDecreaseShield){
        shieldStrength -= shieldStrength - amountToDecreaseShield;
        return shieldStrength;
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Warrior warrior = (Warrior) o;
        return shieldStrength == warrior.shieldStrength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shieldStrength);
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "shieldStrength=" + shieldStrength +
                ", name='" + name + '\'' +
                ", strength=" + strength +
                ", health=" + health +
                ", stamina=" + stamina +
                ", speed=" + speed +
                ", attackPower=" + attackPower +
                '}';
    }
}
