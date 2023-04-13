package eat_schedule.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreDTO {
	private Integer seq;
	private String pictureLocation;
	private String ownerId;
	private String storeName;
	private String location;
	private String telNumber;
	private String ownerComment;
	private String openTime;
	private String closeTime;
	private String howToCome;
	private boolean parking;
	private boolean wifi;
	private boolean animal;
	private boolean group;
	private boolean playroom;
	private boolean disabled;
	
}
