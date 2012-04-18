/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.parties;

import hotelsoftware.model.datainterfaces.GuestData;
import hotelsoftware.model.domain.service.Habitation;
import hotelsoftware.model.domain.service.HabitationData;
import hotelsoftware.util.HelperFunctions;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
public class Guest extends Party implements GuestData {

	private Date birthday;
	private String fname;
	private String lname;
	private Character gender;
	private Collection<Habitation> habitations;

    @Override
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

    @Override
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

    @Override
	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Collection<Habitation> getHabitations() {
		return habitations;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setCurrentHabitations(Collection<Habitation> habitations) {
		this.habitations = habitations;
	}

    @Override
	public Date getBirthday() {
		return birthday;
	}

	public Collection<Habitation> getCurrentHabitations() {
		return habitations;
	}

	private Guest(String fname, String lname, Character gender, Date birthday,
			Address address) {
		this(fname, lname, gender, birthday, address,
				new LinkedList<Habitation>());
	}

	private Guest(String fname, String lname, Character gender, Date birthday,
			Address address, Collection<Habitation> habitations) {
		super(address);
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.birthday = birthday;
		this.habitations = habitations;

	}

	public static Guest create(String fname, String lname, Character gender,
			Date birthday, Address address) {
		return new Guest(fname, lname, gender, birthday, address);
	}

	public void addHabitation(Habitation h) {
		habitations.add(h);
	}

	public void removeHabitation(Habitation h) {
		habitations.remove(h);
	}

	public static Collection<Guest> getGuestByName(String fname, String lname)
			throws CompanyNotFoundException {
		return PartyFacade.getInstance().getGuestByName(fname, lname);
	}

    public Collection<HabitationData> getCurrentHabitationsData()
    {
        return new HelperFunctions<HabitationData, Habitation>().castCollectionUp(getCurrentHabitations());
    }

    public Collection<HabitationData> getHabitationsData()
    {
        return new HelperFunctions<HabitationData, Habitation>().castCollectionUp(getHabitations());
    }
}
