package fr.univavignon.pokedex;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections4.map.UnmodifiableMap;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.Pokemon;

public class RocketPokemonFactory implements IPokemonFactory {

	private static Map<Integer, String> index2name;
	static {
		Map<Integer, String> aMap = new HashMap<Integer, String>();
		aMap.put(-1, "Ash's Pikachu");
		aMap.put(0, "MISSINGNO");
		aMap.put(1, "Bulbasaur");
		aMap.put(2, "Ivysaur");
		aMap.put(3, "Venusaur");
		aMap.put(4, "Charmander");
		aMap.put(5, "Charmeleon");
		aMap.put(6, "Charizard");
		aMap.put(7, "Squirtle");
		aMap.put(8, "Wartortle");
		aMap.put(9, "Blastoise");
		aMap.put(10, "Caterpie");
		aMap.put(11, "Metapod");
		aMap.put(12, "Butterfree");
		aMap.put(13, "Weedle");
		aMap.put(14, "Kakuna");
		aMap.put(15, "Beedrill");
		aMap.put(16, "Pidgey");
		aMap.put(17, "Pidgeotto");
		aMap.put(18, "Pidgeot");
		aMap.put(19, "Rattata");
		aMap.put(20, "Raticate");
		aMap.put(21, "Spearow");
		aMap.put(22, "Fearow");
		aMap.put(23, "Ekans");
		aMap.put(24, "Arbok");
		aMap.put(25, "Pikachu");
		aMap.put(26, "Raichu");
		aMap.put(27, "Sandshrew");
		aMap.put(28, "Sandslash");
		aMap.put(29, "Nidoran");
		aMap.put(30, "Nidorina");
		aMap.put(31, "Nidoqueen");
		aMap.put(32, "Nidoran");
		aMap.put(33, "Nidorino");
		aMap.put(34, "Nidoking");
		aMap.put(35, "Clefairy");
		aMap.put(36, "Clefable");
		aMap.put(37, "Vulpix");
		aMap.put(38, "Ninetales");
		aMap.put(39, "Jigglypuff");
		aMap.put(40, "Wigglytuff");
		aMap.put(41, "Zubat");
		aMap.put(42, "Golbat");
		aMap.put(43, "Oddish");
		aMap.put(44, "Gloom");
		aMap.put(45, "Vileplume");
		aMap.put(46, "Paras");
		aMap.put(47, "Parasect");
		aMap.put(48, "Venonat");
		aMap.put(49, "Venomoth");
		aMap.put(50, "Diglett");
		aMap.put(51, "Dugtrio");
		aMap.put(52, "Meowth");
		aMap.put(53, "Persian");
		aMap.put(54, "Psyduck");
		aMap.put(55, "Golduck");
		aMap.put(56, "Mankey");
		aMap.put(57, "Primeape");
		aMap.put(58, "Growlithe");
		aMap.put(59, "Arcanine");
		aMap.put(60, "Poliwag");
		aMap.put(61, "Poliwhirl");
		aMap.put(62, "Poliwrath");
		aMap.put(63, "Abra");
		aMap.put(64, "Kadabra");
		aMap.put(65, "Alakazam");
		aMap.put(66, "Machop");
		aMap.put(67, "Machoke");
		aMap.put(68, "Machamp");
		aMap.put(69, "Bellsprout");
		aMap.put(70, "Weepinbell");
		aMap.put(71, "Victreebel");
		aMap.put(72, "Tentacool");
		aMap.put(73, "Tentacruel");
		aMap.put(74, "Geodude");
		aMap.put(75, "Graveler");
		aMap.put(76, "Golem");
		aMap.put(77, "Ponyta");
		aMap.put(78, "Rapidash");
		aMap.put(79, "Slowpoke");
		aMap.put(80, "Slowbro");
		aMap.put(81, "Magnemite");
		aMap.put(82, "Magneton");
		aMap.put(83, "Farfetch\'d");
		aMap.put(84, "Doduo");
		aMap.put(85, "Dodrio");
		aMap.put(86, "Seel");
		aMap.put(87, "Dewgong");
		aMap.put(88, "Grimer");
		aMap.put(89, "Muk");
		aMap.put(90, "Shellder");
		aMap.put(91, "Cloyster");
		aMap.put(92, "Gastly");
		aMap.put(93, "Haunter");
		aMap.put(94, "Gengar");
		aMap.put(95, "Onix");
		aMap.put(96, "Drowzee");
		aMap.put(97, "Hypno");
		aMap.put(98, "Krabby");
		aMap.put(99, "Kingler");
		aMap.put(100, "Voltorb");
		aMap.put(101, "Electrode");
		aMap.put(102, "Exeggcute");
		aMap.put(103, "Exeggutor");
		aMap.put(104, "Cubone");
		aMap.put(105, "Marowak");
		aMap.put(106, "Hitmonlee");
		aMap.put(107, "Hitmonchan");
		aMap.put(108, "Lickitung");
		aMap.put(109, "Koffing");
		aMap.put(110, "Weezing");
		aMap.put(111, "Rhyhorn");
		aMap.put(112, "Rhydon");
		aMap.put(113, "Chansey");
		aMap.put(114, "Tangela");
		aMap.put(115, "Kangaskhan");
		aMap.put(116, "Horsea");
		aMap.put(117, "Seadra");
		aMap.put(118, "Goldeen");
		aMap.put(119, "Seaking");
		aMap.put(120, "Staryu");
		aMap.put(121, "Starmie");
		aMap.put(122, "Mr. Mime");
		aMap.put(123, "Scyther");
		aMap.put(124, "Jynx");
		aMap.put(125, "Electabuzz");
		aMap.put(126, "Magmar");
		aMap.put(127, "Pinsir");
		aMap.put(128, "Tauros");
		aMap.put(129, "Magikarp");
		aMap.put(130, "Gyarados");
		aMap.put(131, "Lapras");
		aMap.put(132, "Ditto");
		aMap.put(133, "Eevee");
		aMap.put(134, "Vaporeon");
		aMap.put(135, "Jolteon");
		aMap.put(136, "Flareon");
		aMap.put(137, "Porygon");
		aMap.put(138, "Omanyte");
		aMap.put(139, "Omastar");
		aMap.put(140, "Kabuto");
		aMap.put(141, "Kabutops");
		aMap.put(142, "Aerodactyl");
		aMap.put(143, "Snorlax");
		aMap.put(144, "Articuno");
		aMap.put(145, "Zapdos");
		aMap.put(146, "Moltres");
		aMap.put(147, "Dratini");
		aMap.put(148, "Dragonair");
		aMap.put(149, "Dragonite");
		aMap.put(150, "Mewtwo");
		aMap.put(151, "Mew");

		index2name = UnmodifiableMap.unmodifiableMap(aMap);
	}

	private static int generateRandomStat() {
		int total = 0;
		for (int i = 0; i < 1000000; i++) {
			Random rn = new Random();
			int r = rn.nextInt(2);
			total = total + r;
		}
		return total / 10000;
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		String name;
		if (!index2name.containsKey(index)) {
			name = index2name.get(0);
		} else {
			name = index2name.get(index);
		}
		int attack;
		int defense;
		int stamina;
		double iv;
		if (index < 0) {
			attack = 1000;
			defense = 1000;
			stamina = 1000;
			iv = 0;
		} else {
			attack = RocketPokemonFactory.generateRandomStat();
			defense = RocketPokemonFactory.generateRandomStat();
			stamina = RocketPokemonFactory.generateRandomStat();
			iv = 1;
		}
		return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
	}

}