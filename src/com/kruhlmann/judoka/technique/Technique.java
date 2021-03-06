package com.kruhlmann.judoka.technique;

import java.util.ArrayList;
import java.util.Random;

import com.kruhlmann.judoka.JudokaComponent;
import com.kruhlmann.judoka.entity.Entity;
import com.kruhlmann.judoka.graphics.Animation;
import com.kruhlmann.judoka.sound.Sound;

public class Technique {

	public static Technique O_SOTO_GARI;
	public static Technique UCHI_MATA;
	public static Technique MOROTE_SEOI_NAGE;
	public static Technique ERI_SEOI_NAGE;
	public static Technique O_UCHI_GARI;
	
	public static Technique[] techniquesBack;
	public static Technique[] techniquesForward;
	public static Technique[] techniquesUp;
	public static Technique[] techniquesDown;
	
	public int ID;
	public int YUKO_CHANCE;
	public int WAZA_CHANCE;
	public int IPPON_CHANCE;
	public int SUCCESS_RATE;
	public int COUNTER_CHANCE;
	public int GRIB_BREAK_CHANCE;
	public int ENERGY_LOST_ON_SUCCESS;
	public int ENERGY_LOST_ON_FAILURE;
	public int BALANCE_SUBTRACTION_SELF;
	public int PIXELS_MOVED_ON_STALEMATE;
	public int BALANCE_SUBTRACTION_TARGET;
	
	public boolean BROKE_GRIB;
	public boolean SUCCESS;
	public boolean IS_COUNTER;
	public boolean CAN_COUNTER;
	public boolean CAN_BREAK_GRIB;
	public boolean WILL_MOVE_ON_STALEMATE;

	public ArrayList<Integer> CAN_BE_COUNTERED_TECHNIQUES = new ArrayList<Integer>();
	public ArrayList<Integer> CAN_COUNTER_TECHNIQUES = new ArrayList<Integer>();
	public String NAME;
	public Animation ANIMATION;
	public Animation OPPONENT_ANIMATION;
	
	private Random random;
	
	/**
	 * Constructor
	 * @param ID : id of technique
	 * @param YUKO_CHANCE : chance of scoring yuko
	 * @param WAZA_CHANCE : chance of scoring waza-ari
	 * @param IPPON_CHANCE : chance of scoring ippon
	 * @param ANIMATION_ID : id of associated animation
	 * @param SUCCESS_RATE : rate of successfull throws
	 * @param COUNTER_CHANCE : chance of countering
	 * @param GRIB_BREAK_CHANCE : chance of technique breaking grib
	 * @param ENERGY_LOST_ON_SUCCESS : energy lost on successfull technique
	 * @param ENERGY_LOST_ON_FAILURE : energy lost on failed technique
	 * @param BALANCE_SUBTRACTION_SELF : balance lost on making technique 
	 * @param PIXELS_MOVED_ON_STALEMATE : pixels moved upon making technique
	 * @param BALANCE_SUBTRACTION_TARGET : balance lost (opponent) on making technique
	 * @param IS_COUNTER : technique is counter technique
	 * @param CAN_COUNTER : technique can be countered
	 * @param CAN_BREAK_GRIB : technique can break current grib
	 * @param WILL_MOVE_ON_STALEMATE : technique will cause movement 
	 * @param CAN_COUNTER_TECHNIQUES : techniques, that this technique can counter
	 * @param CAN_BE_COUNTERED_TECHNIQUES : technique, that can counter this technique
	 * @param NAME : formal name of technique
	 */
	public Technique(
			int ID, 
			int YUKO_CHANCE, 
			int WAZA_CHANCE, 
			int IPPON_CHANCE, 
			Animation ANIMATION, 
			Animation ANIMATION_OPPONENT, 
			int SUCCESS_RATE, 
			int COUNTER_CHANCE, 
			int GRIB_BREAK_CHANCE,
			int ENERGY_LOST_ON_SUCCESS,
			int ENERGY_LOST_ON_FAILURE,
			int BALANCE_SUBTRACTION_SELF,
			int PIXELS_MOVED_ON_STALEMATE,
			int BALANCE_SUBTRACTION_TARGET,

			boolean IS_COUNTER,
			boolean CAN_COUNTER,
			boolean CAN_BREAK_GRIB,
			boolean WILL_MOVE_ON_STALEMATE,

			ArrayList<Integer> CAN_BE_COUNTERED_TECHNIQUES,
			ArrayList<Integer> CAN_COUNTER_TECHNIQUES,
			String NAME
			) {
		this.ID = ID;
		this.YUKO_CHANCE = YUKO_CHANCE;
		this.WAZA_CHANCE = WAZA_CHANCE;
		this.IPPON_CHANCE = IPPON_CHANCE;
		this.ANIMATION = ANIMATION;
		this.OPPONENT_ANIMATION = ANIMATION_OPPONENT;
		this.SUCCESS_RATE = SUCCESS_RATE;
		this.COUNTER_CHANCE = COUNTER_CHANCE;
		this.GRIB_BREAK_CHANCE = GRIB_BREAK_CHANCE;
		this.ENERGY_LOST_ON_FAILURE = ENERGY_LOST_ON_FAILURE;
		this.ENERGY_LOST_ON_SUCCESS = ENERGY_LOST_ON_SUCCESS;
		this.BALANCE_SUBTRACTION_SELF = BALANCE_SUBTRACTION_SELF;
		this.BALANCE_SUBTRACTION_TARGET = BALANCE_SUBTRACTION_TARGET;
		this.PIXELS_MOVED_ON_STALEMATE = PIXELS_MOVED_ON_STALEMATE;
		this.IS_COUNTER = IS_COUNTER;
		this.CAN_COUNTER = CAN_COUNTER;
		this.CAN_BREAK_GRIB = CAN_BREAK_GRIB;
		this.WILL_MOVE_ON_STALEMATE = WILL_MOVE_ON_STALEMATE;
		this.CAN_BE_COUNTERED_TECHNIQUES = CAN_BE_COUNTERED_TECHNIQUES;
		this.CAN_COUNTER_TECHNIQUES = CAN_COUNTER_TECHNIQUES;
		this.NAME = NAME;
		random = new Random();
	}
	
	/**
	 * Performs the technique at an entity
	 * @param self : the player doing technique
	 * @param target : the player the technique is being done at
	 */
	public void perform(Entity target, Entity self){
		this.SUCCESS = false;
		this.BROKE_GRIB = false;
		
		//Logic
		self.cooldown = 120;
		
		int roll = random.nextInt(100);
		
		roll += ((400 - target.energy) / 4);
				
		if(roll < this.SUCCESS_RATE){
			JudokaComponent.techniqueTimer = JudokaComponent.timer;
			target.energy -= this.ENERGY_LOST_ON_SUCCESS;
			if(roll <= this.YUKO_CHANCE && roll > this.WAZA_CHANCE){
				self.yukos ++;
				this.SUCCESS = true;
			}
			else if(roll <= this.WAZA_CHANCE && roll > this.IPPON_CHANCE){
				self.wazaaris ++;
				this.SUCCESS = true;
			}
			else if(roll <= this.IPPON_CHANCE){
				self.ippons ++;
				this.SUCCESS = true;
			}else{
				Sound.HIT3.play(false);
			}
			if(SUCCESS) 
				Sound.HIT2.play(false);
		}else{
			Sound.HIT4.play(false);
			self.energy -= this.ENERGY_LOST_ON_FAILURE;
			roll = random.nextInt(100);
			if(roll < this.GRIB_BREAK_CHANCE && this.CAN_BREAK_GRIB){
				this.BROKE_GRIB = true;
			}
		}
	}
	
	public static Technique getTechnique(int id){
		for(Technique t: techniquesBack){
			if(t.ID == id) return t;
		}
		for(Technique t: techniquesForward){
			if(t.ID == id) return t;
		}
		for(Technique t: techniquesUp){
			if(t.ID == id) return t;
		}
		for(Technique t: techniquesDown){
			if(t.ID == id) return t;
		}
		return null;
	}

}
