/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.model.domain.room;

import hotelsoftware.model.DynamicMapper;
import hotelsoftware.model.database.room.DBRoomOption;
import java.util.Collection;

/**
 * 
 * @author Lins Christian (christian.lins87@gmail.com)
 * @author Hubert
 */
public class RoomOption implements RoomOptionData {
	private Integer id;
	private String name;
        
        public RoomOption()
        {
        }

	private RoomOption(String name) {
		this.name = name;
		DBRoomOption.safeNewRoomOption(name);
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
		Collection<DBRoomOption> dbro = DBRoomOption.getRoomOptions();
		return (Collection<RoomOption>) DynamicMapper.map(dbro);
	}
}
