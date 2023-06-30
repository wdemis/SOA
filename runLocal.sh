#!/bin/bash

#############################################################################################################################################
# Project			  :	SOA																													                                                              #
# Description		:	Shell script for starting SOA application in local terminal. This script is useful for both single					              #
#						      process startup as well as scripting multiple processes to start together. When using multiprocess					              #
#						      the script allows for local debugging ports and properties file paths to be overwritten.							                    #
#																																			                                                                      #
# Input Params	:	Properties File  - Optional - path to properties file																                                      #
#					      :	Debug Port		   - Optional - port used for jdwp transport															                                  #
#																																			                                                                      #
# Usage				  :	For defaults      -	./runLocal.sh																				                                                  #
#					      :	For multiprocess	-	./runLocal.sh "/path/to/properties/file" 5006												                                  #
#																																			                                                                      #
# Created by		:	Willy Demis																											                                                          #
# Created date	:	2023-06-30																											                                                          #
# Version			  :	1.0																													                                                              #
#############################################################################################################################################

propLocation=${1:-"./src/main/resources/application.properties"}
debugPort=${2:-5005}

mvn spring-boot:run -Dspring-boot.run.arguments=--spring.config.location="$propLocation" -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=$debugPort"
