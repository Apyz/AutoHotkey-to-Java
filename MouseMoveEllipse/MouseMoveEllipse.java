/*
    MouseMoveEllipse.java
    Pedro Vinnícius
        based on:

    MouseMove_Ellipse.ahk
    Copyright (C) 2010,2013 Antonio França
    This script is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.
    This script is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU Affero General Public License for more details.
    You should have received a copy of the GNU Affero General Public License
    along with this script. If not, see <http://www.gnu.org/licenses/>.
*/

/*========================================================================
 MouseMoveEllipse.java
   https://github.com/pvbernhard/AutoHotkey-to-Java
 
 Moves the mouse using an ellipse instead of a straight line
 
 Based on previous work by MasterFocus
 MouseMove_Ellipse
   http://bit.ly/YNKpPy | https://github.com/MasterFocus/AutoHotkey
   https://git.io/master | http://masterfocus.ahk4.net
   version: 2013-03-06 07:00 BRT

 Based on previous work by Wicked
   http://autohotkey.com/board/topic/56830-function-mousemove-ellipse-91210/

  ========================================================================*/

import java.awt.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("Duplicates") // IntelliJ was being annoying without an actual reason - there's no duplicate code

public class MouseMoveEllipse {

    public static void init(int posX1, int posY1) throws Exception {
        init(posX1, posY1, "");
    }

    public static void init(int posX1, int posY1, String paramOptions) throws Exception{
        Robot robot;

        int[] posX = {0, posX1};
        int[] posY = {0, posY1};
        paramOptions = paramOptions.toUpperCase();

        // Use mouse coordinates if origin is omitted
        int locMouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int locMouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();

        Matcher locMatch;

        // get origin mouse coordinates
        Pattern positionXPattern = Pattern.compile("OX\\d+");
        Pattern positionYPattern = Pattern.compile("OY\\d+");
        locMatch = positionXPattern.matcher(paramOptions);
        if (locMatch.find())
            posX[0] = Integer.parseInt(locMatch.group().substring(2));
        else
            posX[0] = locMouseX;
        locMatch = positionYPattern.matcher(paramOptions);
        if (locMatch.find())
            posY[0] = Integer.parseInt(locMatch.group().substring(2));
        else
            posY[0] = locMouseY;

        // set speed (default is 1)
        double locSpeed = 1;
        Pattern speedPattern = Pattern.compile("S\\d+\\.?\\d*");
        locMatch = speedPattern.matcher(paramOptions);
        if (locMatch.find())
            locSpeed = Double.parseDouble(locMatch.group().substring(1));

        // invert option
        Random randomGenerator = new Random();
        boolean locInv;
        int locInvInt = randomGenerator.nextInt(1); // randomly invert by default
        Pattern invertPattern = Pattern.compile("I[01]");
        locMatch = invertPattern.matcher(paramOptions);
        if (locMatch.find())
            locInvInt = Integer.parseInt(locMatch.group().substring(1));
        locInv = (locInvInt == 1);
        // support relative movements

        if (paramOptions.contains("R")) {
            posX[1] += posX[0];
            posY[1] += posY[0];
        }

        // TODO: Block any mouse input, param "B"

        // B: Width , A: Height
        int locB = Math.abs(posX[0] - posX[1]);
        int locA = Math.abs(posY[0] - posY[1]);

        boolean locTemp = locInv ^ (posX[0] < posX[1]) ^ (posY[0] < posY[1]);
        int locH = posX[(locTemp ? 1 : 0)]; // Center point X
        locTemp = !locTemp;
        int locK = posY[(locTemp ? 1 : 0)]; // Center point Y

        // Original: Mouse Delay saved and set to 1 - not needed.

        int locMultX = 0, locMultY = 0, locX = 0, locY = 0;
        if (locB > locA) { // If distance from pos_X0 to pos_X1 is greater then pos_Y0 to pos_Y1
            locMultX = (posX[0] < posX[1]) ? 1 : (-1);
            locMultY = locMultX * (locInv ? 1 : (-1));
            for (int i = 0; i < locB / locSpeed; i++) {
                locX = (int)(posX[0] + (i * locSpeed * locMultX)); // Add or subtract loc_Speed from X
                // Formula for Y with a given X
                locY = (int) (locMultY * Math.sqrt(Math.pow(locA, 2) * (Math.pow(locX - locH, 2) / Math.pow(locB, 2) - 1) * (-1))) + locK;

                robot = new Robot();
                robot.mouseMove(locX, locY);

                Thread.sleep(3); // or else it will go REALLY fast
            }
        } else { // If distance from pos_Y0 to pos_Y1 is greater then pos_X0 to pos_X1
            locMultY = (posY[0] < posY[1]) ? 1 : (-1);
            locMultX = locMultY * (locInv ? (-1) : 1);
            for (int i = 0; i < locA / locSpeed; i++) {
                locY = (int)(posY[0] + (i * locSpeed * locMultY)); // Add or subtract loc_Speed from Y
                // Formula for X with a given Y
                locX = (int) (locMultX * Math.sqrt(Math.pow(locB, 2) * (Math.pow(locY - locK, 2) / Math.pow(locA, 2) - 1) * (-1))) + locH;

                robot = new Robot();
                robot.mouseMove(locX, locY);

                Thread.sleep(3); // or else it will go REALLY fast
            }
        }

        // Sometimes loop would end with mouse more than "loc_Speed" pixels away from pos_X1,pos_Y1
        robot = new Robot();
        robot.mouseMove(posX[1], posY[1]);

        // TODO: After block mouse input, unblock it.

        // Original: Restore previous mouse delay - uneeded.
    }
}
