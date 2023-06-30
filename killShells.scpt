#!/usr/bin/osascript

#############################################################################################################################################
# Project			:	SOA																													#
# Description		:	AppleScript used in conjunction with bash script (runMany.sh) in order to spin down multiple processes.				#
#						This script is used to properly stop each process instance and kill the terminal window opened for each instance.	#
#						This is a simple helper for use when testing multiple instances for load balanacing and scale out.					#
#																																			#
# Input Params		:	<none>																												#
#																																			#
# Usage				:	./killShells.scpt																									#
#																																			#
# Created by		:	Willy Demis																											#
# Created date		:	2023-06-30																											#
# Version			:	1.0																													#
#############################################################################################################################################

tell application "Terminal"
	activate
	get (windows where name contains "runLocal")
	repeat with w in the result
		set index of w to 1
		tell application "System Events" to keystroke "c" using {control down}
		delay 2
		close w
	end repeat
end tell
