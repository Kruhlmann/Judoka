package com.kruhlmann.judoka.technique;

import java.util.ArrayList;
import java.util.Random;

import com.kruhlmann.judoka.JudokaComponent;
import com.kruhlmann.judoka.entity.Player;
import com.kruhlmann.judoka.entity.Player2;
import com.kruhlmann.judoka.graphics.Animation;
import com.kruhlmann.judoka.level.Level;
import com.kruhlmann.judoka.sound.Sound;

public class Technique {

	public static final Technique O_SOTO_GARI = new Technique(0, 40, 30, 25, Animation.O_SOTO_GARI, Animation.o_soto_gari, 60, 30, 30, 20, 20, 40, 30, 30, false, true, true, true, null, null, "O Soto Gari");
	public static final Technique UCHI_MATA = new Technique(1, 35, 27, 20, Animation.UCHI_MATA, Animation.uchi_mata, 60, 40, 0, 20, 25, 30, 20, 20, false, true, true, false, null, null, "Uchi Mata");
	public static final Technique MOROTE_SEOI_NAGE = new Technique(2, 45, 37, 15, Animation.MOROTE_SEOI_NAGE, Animation.morote_seoi_nage, 50, 0, 10, 8, 7, 15, 20, 15, false, false, true, true, null, null, "Morote Seoi Nage");
	
	
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
	 * Performs the technique at player2
	 * @param self : the player doing technique
	 * @param target : the player the technique is being done at
	 */
	public void performOnP2(Level level){
		this.SUCCESS = false;
		this.BROKE_GRIB = false;
		
		//Logic
		Player2 p2 = level.player2;
		Player p1 = level.player1;
		p1.cooldown = 120;
		
		int roll = random.nextInt(100);
		
		roll += ((400 - p1.energy) / 4);
		System.out.println((400 - p1.energy) / 8);
		
		if(roll < this.SUCCESS_RATE){
			System.out.println(NAME + " was successful");
			JudokaComponent.techniqueTimer = JudokaComponent.timer;
			p2.energy -= this.ENERGY_LOST_ON_SUCCESS;
			if(roll <= this.YUKO_CHANCE && roll > this.WAZA_CHANCE){
				System.out.println("Player 1 scored a yuko with " + NAME);
				p1.yukos ++;
				this.SUCCESS = true;
			}
			else if(roll <= this.WAZA_CHANCE && roll > this.IPPON_CHANCE){
				System.out.println("Player 1 scored a waza with " + NAME);
				p1.wazaaris ++;
				this.SUCCESS = true;
			}
			else if(roll <= this.IPPON_CHANCE){
				System.out.println("Player 1 scored a ippon with " + NAME);
				p1.ippons ++;
				this.SUCCESS = true;
			}else{
				Sound.HIT3.play(false);
			}
			if(SUCCESS) 
				Sound.HIT2.play(false);
		}else{
			System.out.println(NAME + " failed!");
			Sound.HIT4.play(false);
			p1.energy -= this.ENERGY_LOST_ON_FAILURE;
			roll = random.nextInt(100);
			if(roll < this.GRIB_BREAK_CHANCE && this.CAN_BREAK_GRIB){
				System.out.println(NAME + " broke the grib");
				this.BROKE_GRIB = true;
			}
		}
	}
	
	/**
	 * Performs the technique at player1
	 * @param self : the player doing technique
	 * @param target : the player the technique is being done at
	 */
	public void performOnP1(Level level){
		this.SUCCESS = false;
		this.BROKE_GRIB = false;
		
		//Logic
		Player2 p2 = level.player2;
		Player p1 = level.player1;
		p2.cooldown = 120;
		
		int roll = random.nextInt(100);
		
		roll += ((400 - p1.energy) / 4);
		System.out.println((400 - p1.energy) / 8);
				
		if(roll < this.SUCCESS_RATE){
			System.out.println(NAME + " was successful");
			JudokaComponent.techniqueTimer = JudokaComponent.timer;
			p1.energy -= this.ENERGY_LOST_ON_SUCCESS;
			if(roll <= this.YUKO_CHANCE && roll > this.WAZA_CHANCE){
				System.out.println("Player 2 scored a yuko with " + NAME);
				p2.yukos ++;
				this.SUCCESS = true;
			}
			else if(roll <= this.WAZA_CHANCE && roll > this.IPPON_CHANCE){
				System.out.println("Player 2 scored a waza with " + NAME);
				p2.wazaaris ++;
				this.SUCCESS = true;
			}
			else if(roll <= this.IPPON_CHANCE){
				System.out.println("Player 2 scored a ippon with " + NAME);
				p2.ippons ++;
				this.SUCCESS = true;
			}else{
				Sound.HIT3.play(false);
			}
			if(SUCCESS) 
				Sound.HIT2.play(false);
		}else{
			System.out.println(NAME + " failed!");
			Sound.HIT4.play(false);
			p2.energy -= this.ENERGY_LOST_ON_FAILURE;
			roll = random.nextInt(100);
			if(roll < this.GRIB_BREAK_CHANCE && this.CAN_BREAK_GRIB){
				System.out.println(NAME + " broke the grib");
				this.BROKE_GRIB = true;
			}
		}
	}

}
