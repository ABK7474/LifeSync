package com.lifesync.model;

import com.lifesync.model.*;
import com.lifesync.service.*;
import com.lifesync.repository.*;
import com.lifesync.factory.*;
import com.lifesync.exception.*;
import com.lifesync.interfaces.*;
import com.lifesync.util.*;

public class Besin implements IBilgiGetirebilir{
	
	private int besinId;
	private String besinAdi;
	private double kalori;
	private double protein;
	private double karbonhidrat;
	private double yag;
	private double lif;
	
	private Birim gram = Birim.GRAM;
	private Birim kcal = Birim.KCAL;
	
	public Besin(int besinId,String besinAdi,double kalori,double protein,double karbonhidrat,double yag,double lif)
	{
		this.besinId=besinId;
		this.besinAdi=besinAdi;
		this.kalori=kalori;
		this.protein=protein;
		this.karbonhidrat=karbonhidrat;
		this.yag=yag;
		this.lif=lif;
	}

	//region Getters and Setters
	public int getBesinId() { return besinId; }
	
	public String getBesinAdi() { return besinAdi; }
	
	public double getKalori() { return kalori; }
	
	public double getProtein() { return protein; }
	
	public double getKarbonhidrat() { return karbonhidrat; }
	
	public double getYag() { return yag; }
	
	public double getLif() { return lif; }
	//endregion
	
	public String bilgiGetir()
	{
		return  "Besin Adi: " + besinAdi +
				", Kalori Miktari: " + kalori + " " + kcal +
				", Protein Miktari: " + protein + " " + gram +
				", Karbonhidrat Miktari: " + karbonhidrat + " " + gram +
				", Yag Miktari: " + yag + " " + gram +
				", Lif Miktari: " + lif + " " + gram;
	}

	@Override
	public String toString() {
		return "Besin{id=" + besinId + ", ad='" + besinAdi + "', kalori=" + kalori + "}";
	}
}