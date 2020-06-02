package pt.tooyummytogo.facade.dto;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;

public class PosicaoCoordenadas {
	
	private double latitude;
	private double longitude;
	
	/**
	 * Constructor
	 * 
	 * @param lat latitude coordinate
	 * @param lng longitude coordinate
	 */
	public PosicaoCoordenadas(double lat, double lng) {
		this.latitude = lat;
		this.longitude = lng;
	}
	
	/**
	 * Get latitude coordinate
	 * @return latitude latitude coordinate
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Get longitude coordinate
	 * @return longitude longitude coordinate
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * Get distance 
	 * @param o coordinates
	 * @return coordinates
	 */
	public double distanciaEmMetros(PosicaoCoordenadas o) {
		GeodesicData g = Geodesic.WGS84.Inverse(this.latitude, this.longitude, o.latitude, o.longitude);
        return g.s12;  // distance in metres
	}
	
}
