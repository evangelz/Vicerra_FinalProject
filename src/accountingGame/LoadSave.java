package accountingGame;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import accountingGame.screens.SkillTree;
import accountingGame.screens.World;
import accountingGame.sprite.PlayerSprite;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class LoadSave {
	Kryo kryo = new Kryo();
	
	public void save()
	{
		
		kryo.register(World.class);
		kryo.register(PlayerSprite.class);
		kryo.register(SkillTree.class);
		
		Output output;
		try 
		{
			output = new Output(new FileOutputStream("file.bin"));
			kryo.writeObject(output,World.class);
			kryo.writeObject(output,PlayerSprite.class);
			kryo.writeObject(output,SkillTree.class);
			output.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
	public void load()
	{
		Input input;
		try 
		{
			input = new Input(new FileInputStream("file.bin"));
			World world = kryo.readObject(input, World.class);
			PlayerSprite player = kryo.readObject(input, PlayerSprite.class);
			SkillTree skillTree = kryo.readObject(input, SkillTree.class);
			input.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}
