package mda_utilidades;

public class Abecedario {
		public static enum Dir {Ascendente, Descendente};
		
		private char[] mAlfabeto = { '_', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
				'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
				'z' };

		private Dir mDir;

		private int obtenerPosicionEnAlfabeto(char letra) {
			int cont = 0;
			boolean encontre = false;
			while (!encontre && cont < mAlfabeto.length) {
				if (letra == mAlfabeto[cont]) {
					encontre = true;
				} else {
					cont++;
				}
			}
			if (!encontre) {
				cont = -1;
			}
			return cont;
		}

		private int compararLetras(char primerLetra, char segundaLetra) {
			int result = 0;
			int numPrimerLetra = obtenerPosicionEnAlfabeto(primerLetra);
			int numSegundaLetra = obtenerPosicionEnAlfabeto(segundaLetra);

			if (numPrimerLetra < numSegundaLetra) {
				if (Dir.Ascendente == mDir) {
					result = 1;
				} else {
					result = -1;
				}
			}
			if (numPrimerLetra > numSegundaLetra) {
				if (Dir.Ascendente == mDir) {
					result = -1;
				} else {
					result = 1;
				}
			}

			return result;
		}
		
		/*
		private static int compararInts(int primerNumero, int segundoNumero, Enums.Dir pDir) {
			int result = 0;
			if (primerNumero < segundoNumero) {
				if (pDir == Enums.Dir.Ascendente) {
					result = 1;
				} else {
					result = -1;
				}
			}

			if (primerNumero > segundoNumero) {
				if (pDir == Enums.Dir.Ascendente) {
					result = -1;
				} else {
					result = 1;
				}
			}

			return result;
		}
		*/

		public int compararStrings(String primerPalabra, String segundaPalabra, Dir pDir) {
			mDir = pDir;

			primerPalabra = primerPalabra.replaceAll("[^a-zA-Z0-9_]", "").toLowerCase();
			int largoPrimera = primerPalabra.length();
			int cont = 0;

			segundaPalabra = segundaPalabra.replaceAll("[^a-zA-Z0-9_]", "").toLowerCase();
			int largoSegunda = segundaPalabra.length();

			int resultado = 0;

			while (cont < largoPrimera && cont < largoSegunda && resultado == 0) {
				int comparacion = compararLetras(primerPalabra.charAt(cont), segundaPalabra.charAt(cont));
				if (comparacion == 0) {
					cont++;
				} else {
					resultado = comparacion;
				}
			}

			// si no hay resultados conclusivos todas las letras evaluadas hasta ahora son iguales
			if (resultado == 0) {
				// si tienen el mismo largo y todas las letras son iguales
				if (largoPrimera == largoSegunda) {
					return resultado;
				}

				// si la primer palabra es mas larga que la segunda, revisamos el resto de los caracteres
				// de la primera contra el primero de la segunda
				if (largoPrimera > largoSegunda) {
					while (cont < largoPrimera && resultado == 0) {
						int comparacion = compararLetras(primerPalabra.charAt(cont), segundaPalabra.charAt(0));
						if (comparacion == 0) {
							cont++;
						} else {
							resultado = comparacion;
						}
					}

					// todos los caracteres son iguales, la palabra mas corta va primero
					if (resultado == 0) {
						resultado = -1;
					}
				}

				// si la segunda palabra es mas larga que la primera, revisamos el resto de los caracteres
				// de la segunda contra el primero de la primera
				if (largoSegunda > largoPrimera) {
					while (cont < largoSegunda && resultado == 0) {
						int comparacion = compararLetras(primerPalabra.charAt(0), segundaPalabra.charAt(cont));
						if (comparacion == 0) {
							cont++;
						} else {
							resultado = comparacion;
						}
					}

					// todos los caracteres son iguales, la palabra mas corta va primero
					if (resultado == 0) {
						resultado = 1;
					}
				}
			}
			return resultado;
		}
		
		public Abecedario() {
			mDir = Dir.Descendente;
		}
	
	
	

}
