#!/usr/bin/osascript

#############################################################################################################################################
# Project			  :	SOA																													                                                              #
# Description		:	AppleScript used in conjunction with bash script (runLocal.sh) in order to spin up multiple processes.				            #
#						      This script uses supplied arguments to create new terminal window instances running the SOA application				            #
#						      and is also responsible for positioning the termianl windows across the bottom of the screen for readability.		          #
#																																			                                                                      #
# Input Params	:	Properties Filepath	- Required - path to unique properties file for this instance									                        #
#					      :	Debug Port			    - Required - port to use for jdwp transport														                                #
#					      :	Lowest Position		  - Required - position on screen for lowest terminal window. This is Y screen resolution - 217	        #
#																																			                                                                      #
# Usage				  :	Called from bash script (./runMany.sh) as: 																			                                          #
#								      osascript initiateShell.scpt "/path/to/properties" debugPort windowsPosition								                          #
#																																			                                                                      #
# Created by		:	Willy Demis																											                                                          #
# Created date	:	2023-06-30																											                                                          #
# Version			  :	1.0																													                                                              #
#############################################################################################################################################

    on run argv -- argv is a list of strings
		tell application "Terminal"
			do script ("cd ~/Development/Personal/SOA/ && ./runLocal.sh " & item 1 of argv & " " & item 2 of argv)
			set leftOfScreenToLeftOfWindow to 55
			set topOfScreenToTopOfWindow to item 3 of argv as number
			set leftOfScreenToRightOfWindow to 1781
			set topOfScreenToBottomOfWindow to topOfScreenToTopOfWindow + 217 
			set bounds of front window to {leftOfScreenToLeftOfWindow, topOfScreenToTopOfWindow, leftOfScreenToRightOfWindow, topOfScreenToBottomOfWindow} 
		end tell
    end run

