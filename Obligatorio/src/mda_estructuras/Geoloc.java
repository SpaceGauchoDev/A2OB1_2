package mda_estructuras;

public class Geoloc {
	public double lat;
	public double lon;
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
	
	public boolean equals(Geoloc pGeoloc) {
		return (pGeoloc.lat == lat && pGeoloc.lon == lon);
	}
	
	public boolean esMismaGeoloc(double pLat, double pLon) {
		return (pLat == lat && pLon == lon);
	}
	
	public Geoloc() {
		lat = 0;
		lon = 0;
	}
	
	public String toString() {
		return "lat: "+ lat + "lon: "+ lon;
	}
	
	public Geoloc(double pLat, double pLon) {
		lat = pLat;
		lon = pLon;
	}
}
