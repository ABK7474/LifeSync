package com.lifesync.exception;

import com.lifesync.model.*;
import com.lifesync.service.*;
import com.lifesync.repository.*;
import com.lifesync.factory.*;
import com.lifesync.exception.*;
import com.lifesync.interfaces.*;
import com.lifesync.util.*;


public class KimlikDogrulamaHatasi extends Exception {
	public KimlikDogrulamaHatasi(String mesaj) {
		super(mesaj);
	}
}
