import java.awt.*;
import javax.swing.JFrame;

public class Lienzo extends Canvas
{
	private Cuadrado cuadrados[];
	
	public void pintar(Cuadrado cuadrados[])
	{
		this.cuadrados = cuadrados;
	}

	public void paint(Graphics g)
	{
		for (Cuadrado cuadrado:this.cuadrados) {
			if(cuadrado!=null)
			{
				g.setColor(Color.BLUE);
				g.drawRect(cuadrado.getX(), cuadrado.getY(), cuadrado.getLado(), cuadrado.getLado()); 
				if (cuadrado.isRELLENO())
					g.fillRect(cuadrado.getX(), cuadrado.getY(), cuadrado.getLado(), cuadrado.getLado())
			}
		}
	}
}