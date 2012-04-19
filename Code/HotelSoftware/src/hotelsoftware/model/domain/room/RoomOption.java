/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

<<<<<<< HEAD
<<<<<<< HEAD
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.database.room.DBRoomoption;
import hotelsoftware.model.domain.parties.CompanyType;

import java.util.Collection;
=======
import hotelsoftware.model.database.room.DBRoomOption;
>>>>>>> 78813edac85f434f28a193314799cd7ac88d0518
import java.util.List;
=======
import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.DBRoomOption;
import java.util.Collection;
>>>>>>> ab2a0ab77da37d2f2224d61b9f15bb19d1ac0bef

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
<<<<<<< HEAD
public class RoomOption implements RoomOptionData {
	private Integer id;
	private String name;

	private RoomOption(String name) {
		this.name = name;
		DBRoomoption.safeNewRoomOption(name);
	}

	public RoomOption create(String name) {
		return new RoomOption(name);
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (this.id == null)
			this.id = id;
	}

	public static Collection<RoomOption> getRoomOptions() {
		Collection<RoomOption> dbro = (Collection<RoomOption>) DBRoomoption
				.getRoomoptions();
		return (Collection<RoomOption>) DynamicMapper.map(dbro);
	}

=======
public class RoomOption implements RoomOptionData
{    
    private String name;

    public RoomOption()
    {}
    
    public static RoomOption create(String name)
    {
        return new RoomOption(name);
    }
    
    private RoomOption(String name)
    {        
        this.name = name;
    }
    
    @Override
    public String getName()
    {
        return name;
    }
    
    public static Collection<RoomOption> getRoomOptions ()
    {
        return (Collection<RoomOption>) DynamicMapper.mapCollection(DBRoomOption.getRoomOptions());
    }
    
    
>>>>>>> 78813edac85f434f28a193314799cd7ac88d0518
}
