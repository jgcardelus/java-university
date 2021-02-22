import java.awt.*; // Import everything inside java.awt

public abstract class Figura {
	private int x;
	private int y;
	private boolean relleno;
	private Color color;

	// Most comprehensive constructor
	Figura(int x, int y, boolean relleno, Color color) {
		this.setX(x);
		this.setY(y);
		this.setRelleno(relleno);
		this.setColor(color);	
	}

	public void setX(int x) {
		// Possible upgrades, pair with Lienzo to avoid
		// setting a Figura outside canvas
		if (x >= 0) {
			this.x = x;
		}
	}

	public void setY(int y) {
		if (y >= 0) {
			this.y = y;
		}
	}

	public void setRelleno(boolean relleno) {
		this.relleno = relleno;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean getRelleno() {
		return this.relleno;
	}

	// Method for sintatic bliss
	public boolean isRelleno() {
		return this.getRelleno();
	}

	public Color getColor() {
		return this.color;
	}

	public String getInfo() {
		return "\tPosición: (" + this.getX() + ", " + this.getY() + ")\n\tRelleno: " + this.getRelleno() + "\n\tColor: " + this.getColor().toString();
	}
}

