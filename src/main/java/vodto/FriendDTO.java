package vodto;

public class FriendDTO {
	
	private String friendName;
	private String mobile;
	private String addr;
	
	public FriendDTO(String friendName, String mobile, String addr) {
		super();
		this.friendName = friendName;
		this.mobile = mobile;
		this.addr = addr;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "FriendDTO [friendName=" + friendName + ", mobile=" + mobile + ", addr=" + addr + "]";
	}
	
	
}
