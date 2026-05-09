package com.lifesync.model;

import com.lifesync.model.*;
import com.lifesync.service.*;
import com.lifesync.repository.*;
import com.lifesync.factory.*;
import com.lifesync.exception.*;
import com.lifesync.interfaces.*;
import com.lifesync.util.*;


public class Hedef {
    private double hedefKilo;
    private double hedefKalori;
    private double hedefProtein;
    private double hedefSu;

    public Hedef(double hedefKilo, double hedefKalori, double hedefProtein, double hedefSu) {
        this.hedefKilo = hedefKilo;
        this.hedefKalori = hedefKalori;
        this.hedefProtein = hedefProtein;
        this.hedefSu = hedefSu;
    }

    public double getHedefKilo() { return hedefKilo; }
    
    public double getHedefKalori() { return hedefKalori; }
    
    public double getHedefProtein() { return hedefProtein; }
    
    public double getHedefSu() { return hedefSu; }

    @Override
    public String toString() {
        return "Hedef{kilo=" + hedefKilo + ", kalori=" + hedefKalori + ", protein=" + hedefProtein + ", su=" + hedefSu + "}";
    }
}