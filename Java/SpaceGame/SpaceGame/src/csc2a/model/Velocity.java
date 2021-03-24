package csc2a.model;

public class Velocity {
	
		private double X;
		private double Y;
		
		public Velocity(double X, double Y) {
			this.X = X;
			this.Y = Y;
		}
		/**
		 * @return the x
		 */
		public double getX() {
			return X;
		}
		/**
		 * @param x the x to set
		 */
		public void setX(double x) {
			X = x;
		}
		/**
		 * @return the y
		 */
		public double getY() {
			return Y;
		}
		/**
		 * @param y the y to set
		 */
		public void setY(double y) {
			Y = y;
		}
	
}
