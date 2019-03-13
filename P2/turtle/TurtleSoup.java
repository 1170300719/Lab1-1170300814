/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;
import java.util.*;
import java.util.List;
import java.util.Set;

import turtle.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	turtle.draw();
        turtle.forward(sideLength);
        turtle.turn(90.00);
        turtle.forward(sideLength);
        turtle.turn(90.00);
        turtle.forward(sideLength);
        turtle.turn(90.00);
        turtle.forward(sideLength);
        turtle.turn(90.00);
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        return (double)((sides-2)*180)/sides;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	return (int) Math.round(360/(180-angle));
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	turtle.turn(270);
        double angle ;
        angle= 360.0 - calculateRegularPolygonAngle(sides);
        for(int i = 0; i < sides; i++)
        {
            turtle.forward(sideLength);
            turtle.turn(angle);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	double angle = Math.atan2(targetX - currentX, targetY - currentY) * 180 / Math.PI - currentBearing;//弧度转为角度-当前的轴承
        if (angle < 0)
            angle += 360;//将负角变成正角
        return  angle;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
    	double bearing = 0;
        List<Double> Angle = new ArrayList<>();
        int length = xCoords.size();//x值集合的大小
        for (int i = 0; i < length-1; i++) 
        {
            int a1, b1, a2, b2;
            a1 = xCoords.get(i);
            a2 = xCoords.get(i + 1);
            b1 = xCoords.get(i);
            b2 = yCoords.get(i + 1);
            double angle = calculateBearingToPoint(bearing,a1,b1,a2,b2);
            Angle.add(i, angle);//添加
            bearing = angle;
        }
        return Angle;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    static Set<Point> ans = new HashSet<Point>();
    public static Set<Point> convexHull(Set<Point> points) {
    	//采用了递归的方法分成了小问题来计算凸包问题。
        	if ( points.size() <= 2 )//如果点的数量小于2就是直接返回
        		return points;
        	List<Point> ps = new ArrayList<Point>(points);
        	ch(ps,true);//上图包的计算       	
        	ch(ps,false);//下图包的计算
        	return ans;
        }       
        public static void ch(List<Point> points, boolean up){
        	
        	Collections.sort(points, new Comparator<Point>() {//排序，找x轴最大和最小
                public int compare(Point o1, Point o2) {
                    return (int) (o1.x() != o2.x() ? o1.x() - o2.x() : o1.y() - o2.y());
                }
            });
        	int fir = 0;
            int lst = points.size() - 1;

            
            ans.add(points.get(fir));//加入点集
            ans.add(points.get(lst));


            if (points.size() == 2)//点集里面有两个点的话就返回
                return;

            boolean isLine = true;
            for (int i = 0; i < points.size(); i++) {
                if (i == fir || i == lst)
                    continue;
                if (calcuTriangle(points.get(fir), points.get(lst), points.get(i)) != 0) {//判断是否构成一个三角形
                    isLine = false;//不是直线
                    break;
                }
            }
            
            if (isLine) {
                return;
            }

          
            int maxIndex = -1;
            double max = 0;
            for (int i = 0; i < points.size(); i++) {
                if (i == fir || i == lst)
                    continue;
                
                if (up && calcuTriangle(points.get(fir), points.get(lst), points.get(i)) > max) {//上凸包计算
                    maxIndex = i;
                    max = calcuTriangle(points.get(fir), points.get(lst), points.get(i));
                }
                
                if (!up && -calcuTriangle(points.get(fir), points.get(lst), points.get(i)) > max) {//下图包计算
                    maxIndex = i;
                    max = -calcuTriangle(points.get(fir), points.get(lst), points.get(i));
                }
            }

            
            if (maxIndex == -1) {//找不到就返回
                return;
            }

            
            List<Point> c1 = new ArrayList<>();//开始分成小问题
            split(fir, maxIndex, points, c1, up);
            ch(c1,up);

            List<Point> c2 = new ArrayList<>();
            split(maxIndex, lst, points, c2, up);
            ch(c2,up);
        }

       
        private static void split(int a1, int a2, List<Point> points, List<Point> part, boolean up) {//分成小问题
            for (int i = 0; i < points.size(); i++) {
                if (i == a1 || i == a2) {
                    part.add(points.get(i));
                    continue;
                }
                if (up && calcuTriangle(points.get(a1), points.get(a2), points.get(i)) >= 0) {
                    part.add(points.get(i));
                }

                if (!up && calcuTriangle(points.get(a1), points.get(a2), points.get(i)) <= 0) {
                    part.add(points.get(i));
                }
            }
        }

        
 
        public static double calcuTriangle(Point a1, Point a2, Point a3) {//计算面积
            return a1.x() * a2.y() + a3.x() * a1.y() + a2.x() * a3.y() - a3.x() * a2.y() - a2.x() * a1.y() - a1.x() * a3.y();
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        for(int i=1;i<=185;i++)
          {
          	if(i%4==0)turtle.color(PenColor.BLACK);
          	if(i%4==1)turtle.color(PenColor.BLUE);
          	if(i%4==2)turtle.color(PenColor.CYAN);
          	if(i%4==3)turtle.color(PenColor.YELLOW);
//          	if(i%5==4)turtle.color(PenColor.GREEN);
          	turtle.forward(i);
          	turtle.turn(91);
          }
          
      turtle.draw();
        

    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        
        drawPersonalArt(turtle);
        // draw the window
        turtle.draw();
    }

}
