#!/bin/sh
#
# SCRIPT: Template
# AUTHOR: Elroy Kanye
# DATE: 
# 1.1.A (Valid are A, B, D, T and P)
#       (For Alpha, Beta, Dev, Test and Production)
#
# PLATFORM: Linux
#
# PURPOSE: Setup script for the environment. Can be used as seen fit by the dev
#
# REV LIST:
#       DATE: 17-09-2021
#       By: Elroy Kanye
#       Modification: Run xampp server
#
# set -n    # Uncomment to check script syntax, without execution.
#           # NoteL Do not forget to put comment back.
# set -x    # Uncomment to debug this shell script
#
############################################################
#       DEFINE FILES AND VARIABLES HERE
############################################################

LAMPP_DIR="/opt/lampp/lampp"

############################################################
#       DEFINE FUNCTIONS HERE
############################################################



############################################################
#       BEGINNING OF MAIN
############################################################

sudo $LAMPP_DIR start

############################################################
#       END OF SCRIPT
############################################################
