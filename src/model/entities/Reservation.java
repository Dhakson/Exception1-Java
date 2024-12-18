package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer RoomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
		
	}
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut)  {
		if(!checkOut.after(checkIn)) {
			throw new DomainException( "Check-out date must be after check-in date");
		}
		RoomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	public Integer getRoomNumber() {
		return RoomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		RoomNumber = roomNumber;
	}
	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date chekIn, Date checkOut) {
		Date now = new Date();
		
		if(checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException(" Reservation dates for update must be future dates");
		}
		if(!checkOut.after(checkIn)) {
			throw new DomainException( "Check-out date must be after check-in date");
		}
		this.checkIn = chekIn;
		this.checkOut = checkOut;
	}
	
	@Override
	
	public String toString() {
		return "Room "
				+ RoomNumber
				+ ", check-in: " 
				+ sdf.format(checkIn) 
				+ ", check-out: " 
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nigths";
	}
	

}
