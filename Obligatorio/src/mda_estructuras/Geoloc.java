package mda_estructuras;

public class Geoloc {
	double lat;
	double lon;
	/*
	                  ,_   .  ._. _.  .
	           , _-\','|~\~      ~/      ;-'_   _-'     ,;_;_,    ~~-
	  /~~-\_/-'~'--' \~~| ',    ,'      /  / ~|-_\_/~/~      ~~--~~~~'--_
	  /              ,/'-/~ '\ ,' _  ,y = latitude = N-S        ._/-, /~
	  ~/-'~\_,       '-,| '|. '   ~  ,^ /'~                /    /_  /~
	.-~      '|        '',\~|\       _║~     ,_  ,               /|
	          '\        /'~          |║/~\\,-,~  \ "         ,_,/ |
	           |       /          ════╬════> x = longitude = W-E /
	            \   __-\           '/ ║    ~ |\  \_          /  ~
	  .,         '\ |,  ~-_      - |  ║       \\_' ~|  /\  \~ ,
	               ~-_'  _;       '\           '-,   \,' /\/  |
	                 '\_,~'\_       \_ _,       /'    '  |, /|'
	                   /     \_       ~ |      /         \  ~'; -,_.
	                   |       ~\        |    |  ,        '-_, ,; ~ ~\
	                    \,      /        \    / /|            ,-, ,   -,
	                     |    ,/          |  |' |/          ,-   ~ \   '.
	                    ,|   ,/           \ ,/              \       |
	                    /    |             ~                 -~~-, /   _
	                    |  ,-'                                    ~    /
	                    / ,'                                      ~
	                    ',|  ~
	                      ~'              
	 */
	
	public boolean esMismaGeoloc(double pLat, double pLon) {
		return (pLat == lat && pLon == lon);
	}
	
	public Geoloc() {
		lat = 0;
		lon = 0;
	}
	
	public Geoloc(double pLat, double pLon) {
		lat = pLat;
		lon = pLon;
	}
}
