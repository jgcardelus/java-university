package shapes;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.IOConfig;

public abstract class TwoAxisShape extends XShape {
	private Point startPoint;
	private Point endPoint;

	private Point point;
	private Size size;

	public static final Size MIN_SIZE = new Size(5, 5);

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = new Point(point.getX(), point.getY());
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@Override
	public void canvasMouseDragged(MouseEvent e) {
		if (!this.isDefined()) {
			Point mousePosition = new Point(e.getX(), e.getY());
			if (this.startPoint == null) {
				this.startPoint = mousePosition;
			} else {
				this.endPoint = mousePosition;
				this.setSizeAndPoint(this.startPoint, this.endPoint);
			}
		}
	}

	@Override
	public void canvasMouseReleased(MouseEvent e) {
		if (!this.isDefined()) {
			this.endPoint = new Point(e.getX(), e.getY());
			if (this.startPoint != null) {
				this.setSizeAndPoint(this.startPoint, this.endPoint);
				this.setDefined(true);
			}
		}
	}

	public void setSizeAndPoint(Point startPoint, Point endPoint) {
		int width = endPoint.getX() - startPoint.getX();
		int height = endPoint.getY() - startPoint.getY();
		this.setPoint(startPoint);

		if (startPoint.getX() > endPoint.getX()) {
			this.point.setX(endPoint.getX());
			width = startPoint.getX() - endPoint.getX();
		}

		if (startPoint.getY() > endPoint.getY()) {
			this.point.setY(endPoint.getY());
			height = startPoint.getY() - endPoint.getY();
		}
		
		this.size = new Size(width, height);
	}

	@Override
	public void moveBy(Size size) {
		this.point.moveBy(size);
	}

	@Override
	public abstract void paint(Graphics graphics);
	@Override
	public abstract XShape emptyClone();

	@Override
	public String attributesToCsv() {
		StringBuilder csvData = new StringBuilder();
		csvData.append(this.point.getX()).append(IOConfig.CSV_SEPARATOR);
		csvData.append(this.point.getY()).append(IOConfig.CSV_SEPARATOR);
		csvData.append(this.size.getWidth()).append(IOConfig.CSV_SEPARATOR);
		csvData.append(this.size.getHeight()).append(IOConfig.CSV_SEPARATOR);
		csvData.append(super.attributesToCsv());
		return csvData.toString();
	}

	@Override
	public XShape fromCsv(LinkedList<String> shapeBuffer) {
		List<String> chunks = Arrays.asList(shapeBuffer.get(0).split(IOConfig.CSV_SEPARATOR));
		this.setAttributesFromCsv(chunks);

		Point point = this.getPointFromCsv(chunks);
		Size size = this.getSizeFromCsv(chunks);

		this.setPoint(point);
		this.setSize(size);

		return this;
	}
}
