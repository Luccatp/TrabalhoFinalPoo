import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageFactory {
	private static ImageFactory imgf = new ImageFactory();
	private Map<String, Image> images;

	public static ImageFactory getInstance() {
		return (imgf);
	}

	private ImageFactory() {
		images = new HashMap<>();
	}

	private String id2File(String imgId) {
		switch (imgId) {
			case "img11":
				return ("/imagens/11.png");
			case "img12":
				return ("/imagens/12.png");
			case "img13":
				return ("/imagens/13.png");
			case "img21":
				return ("/imagens/21.png");
			case "img22":
				return ("/imagens/22.png");
			case "img23":
				return ("/imagens/23.png");
			case "img31":
				return ("/imagens/31.png");
			case "img32":
				return ("/imagens/32.png");
			case "img33":
				return ("/imagens/33.png");
			case "img41":
				return ("/imagens/41.png");
			case "img42":
				return ("/imagens/42.png");
			case "img43":
				return ("/imagens/43.png");
			case "img51":
				return ("/imagens/51.png");
			case "img52":
				return ("/imagens/52.png");
			case "img53":
				return ("/imagens/53.png");
			case "img61":
				return ("/imagens/61.png");
			case "img62":
				return ("/imagens/62.png");
			case "img63":
				return ("/imagens/63.png");
			case "img71":
				return ("/imagens/71.png");
			case "img72":
				return ("/imagens/72.png");
			case "img73":
				return ("/imagens/73.png");
			case "img81":
				return ("/imagens/81.png");
			case "img82":
				return ("/imagens/82.png");
			case "img83":
				return ("/imagens/83.png");
			case "img91":
				return ("/imagens/91.png");
			case "img92":
				return ("/imagens/92.png");
			case "img93":
				return ("/imagens/93.png");
			case "imgBck":
				return ("/imagens/Back.png");
			default:
				throw new IllegalArgumentException("Invalid image Id");
		}
	}

	public ImageView createImage(String imgId) {
		Image img = images.get(imgId);
		if (img == null) {
			img = new Image(id2File(imgId));
			images.put(imgId, img);
		}

		ImageView imgv = new ImageView(img);
		return (imgv);
	}
}