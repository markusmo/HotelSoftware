/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.parties.DBCompanyType;
import hotelsoftware.model.database.room.DBRoomoption;
import hotelsoftware.model.domain.parties.CompanyType;

import java.util.Collection;
import java.util.List;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 */
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

}
