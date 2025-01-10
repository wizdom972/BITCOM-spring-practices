package soundsystem;

import org.springframework.stereotype.Component;

@Component
public class CDPlayer {
	private final CompactDisc cd;

	public CDPlayer(CompactDisc cd) {
		this.cd = cd;
	}

	public String play() {
		return cd.play();
	}
}
