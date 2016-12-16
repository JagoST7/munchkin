package ru.amfitel.jagost.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * Created by estarcev on 14.12.2016.
 */
public class BasicSkin {

	public static Skin getSkin(Class... classes){
		//Create a font
		BitmapFont font = new BitmapFont();
		Skin skin = new Skin();
		skin.add("default", font);

		//Create a texture
		Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("background", new Texture(pixmap));

		//Create a button style
		for(Class one : classes) {

			if(one == TextButton.class) {
				TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
				style.up = skin.newDrawable("background", Color.GRAY);
				style.down = skin.newDrawable("background", Color.DARK_GRAY);
				style.checked = skin.newDrawable("background", Color.DARK_GRAY);
				style.over = skin.newDrawable("background", Color.SKY);
				style.font = skin.getFont("default");
				style.disabled = skin.newDrawable("background", Color.GRAY);
				style.disabledFontColor = Color.LIGHT_GRAY;
				skin.add("default", style);
			} else if (one == TextField.class) {
				TextField.TextFieldStyle style = new TextField.TextFieldStyle();
				style.font = skin.getFont("default");
				style.fontColor = Color.WHITE;
				style.background = skin.newDrawable("background", Color.GRAY);
				style.cursor = skin.newDrawable("background", Color.LIGHT_GRAY);
				style.cursor.setMinWidth(2f);
				skin.add("default", style);
			}


		}
		return skin;
	}
}
