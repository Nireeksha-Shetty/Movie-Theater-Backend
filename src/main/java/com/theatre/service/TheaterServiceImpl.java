package com.theatre.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.theatre.entity.Address;
import com.theatre.entity.Row;
import com.theatre.entity.Seat;
import com.theatre.entity.SeatBooking;
import com.theatre.entity.Theater;
import com.theatre.exception.NoContentException;
import com.theatre.exception.UserAlreadyExistException;
import com.theatre.repository.AddressRepository;
import com.theatre.repository.RowRepository;
import com.theatre.repository.TheaterRepository;

@Service
public class TheaterServiceImpl implements TheaterService{
	
	
	@Autowired
	RowRepository rowrepository;

	@Autowired
	TheaterRepository theaterRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public String saveTheater(Theater theater) {
		/*
		 * 	Pseudo Code
		 * 		1. Create 15 Rows
		 * 		2. Create Seats for each Row
		 * 		3. Set required values for each seats
		 * 		4. Store it in Row and Row in Theater																				
		 */
		List<Row> rows = new ArrayList<Row>();
		List<Row> rowcollection = new ArrayList<Row>();		
		rows  =  theater.getRow();
		
		for(Row rowi :rows) {
			int TotalSeats = rowi.getTotalSeats();
			List<Seat> listofseats = new ArrayList<Seat>();
			
			for(int i=0;i<TotalSeats;i++) {
				Seat seat = new Seat();
				seat.setBooking(SeatBooking.NOTBOOKED);
				listofseats.add(seat);            
			}
			
			rowi.setSeats(listofseats);
			rowcollection.add(rowi);
		}
		
		theater.setRow(rowcollection);
		theaterRepository.save(theater);
		
		return String.format(" %s has been successfully stored",theater.getName());
	}
	
	@Override
	public String validateAndSaveTheater(Theater theater) throws UserAlreadyExistException {
		/*
		 * 	Pseudo Code
		 * 		1. Find all the theaters by Name
		 * 		2. See if we have any rows with same name already in Database
		 * 		3. If yes throw error saying User Already Exists
		 * 		4. Else Send the data to saveTheater and return the message																			
		 */
		Theater theaterdetails = findByname(theater.getName());
		
		int id = -1;
		if (theaterdetails != null) {
			throw new UserAlreadyExistException("User Already Exists","DB0001");
		} else {
			String message = saveTheater(theater);
			return message;
		}		
	}
	
//	checking whether the theatre id exists or not.
	@Override
	public boolean isIdExists(int id) {
		Optional<Theater> theatre = theaterRepository.findById(id);
		return theatre.isPresent();
	}
	
	@Override
	public List<Theater> getTheaters() throws NoContentException{
		/*
		 * Pseudo Code
		 * 	1. Create List of Object of Theater Class
		 * 	2. Using findAll from Repository fetch all the theaters present
		 * 	3. if there are theaters send data
		 * 	4. else throw related exception
		 */
		
		List<Theater> allTheaters = new ArrayList<Theater>();
		allTheaters = theaterRepository.findAll();
		
		if(allTheaters.isEmpty()) {
			throw new NoContentException("All theaters are currently unavailable","DB000");
		}
		else {
			return allTheaters;
		}
	}
  
//    performing operation to peform  update theatres.
    @Override
	public String updateTheater(Theater theater) {
//		 performing saving operation
	    theaterRepository.save(theater);
		return "Updated Successfully";
	}
     
//   performing operation to perform  delete theaters.
	@Override
	public String deleteTheater(int theaterId) {
//		deletes theater with the particular id from the database.
		theaterRepository.deleteById(theaterId);
		return "Deleted Successfully";
	}

//	performing operation to get theaters with that particular city.
	@Override
	public List<Theater> getByCity(String city) {
//		return list of theaters in that particular city from the database
		return theaterRepository.findByCity(city);
	}

//	perform operation to get the row with that name.
	@Override
	public Row findByRowname(String name) {
//		returns the row with that name.
		return rowrepository.findByName(name);
	}
	
//	performing operation to get theater details with that particular theater name.
	public Theater findByname(String name) {
//		returns the theater details with that theatre name
		return theaterRepository.findByName(name);
	}
	
//performing operation to get theatre details with particular address field.
    @Override
	public List<Theater> getByAddress(String input) {
//    	returns theater details with that theater address.
    	 return theaterRepository.findByAddress(input);
    }
}