#!/bin/bash

#############################################################################################################################################
# Project			  :	SOA																													                                                              #
# Description		:	Shell script for starting SOA application in local terminal with multiple processes. This script is					              #
#						      used in conjunction with an AppleScript (initiateShell.scpt) to create a terminal for each process					              #
#						      in which each process has a unique properties file and a unique debug port. The properties file for each			            #
#						      instance is generated from the root application.properties file in which the server port is incremented by 1.		          #
#						      Additionally the AppleScript will stack the terminal windows across the bottom of the screen for easy viewing		          #
#						      of all processes at once.																							                                                    #
#																																			                                                                      #
# Input Params	:	Number of Instances	- Required - number of unique SOA instances to spin up											                          #
#																																			                                                                      #
# Usage				  :	./runMany.sh 3	<---- Spins up three instances																		                                        #
#																																			                                                                      #
# Created by		:	Willy Demis																											                                                          #
# Created date	:	2023-06-30																											                                                          #
# Version			  :	1.0																													                                                              #
#############################################################################################################################################

numInstances=$1
serverPortStart=8080
debugPortStart=5005
lowestPosition=1120

for ((i=1; i<=$numInstances; i++))
do
	source="./src/main/resources/application.properties"
	target="./properties/application$i.properties"
	serverPortReplace=$((serverPortStart + i - 1))
	debugPortReplace=$((debugPortStart + i - 1))
	windowPosition=$(( $lowestPosition - 217*$i ))

	cat $source | sed 's/'$serverPortStart'/'$serverPortReplace'/g' > $target
	
	osascript initiateShell.scpt $target $debugPortReplace $windowPosition

done

