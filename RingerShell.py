#!/usr/bin/python
# -*- coding: UTF-8 -*-

'''
 Ringer API Client 

@author: alessio
'''

import sys
import string
import getpass
import httplib as http
import json
import traceback

RINGER_SHELL_VERSION = "0.1.0"
RINGER_API_HOST = 'ringer-sta.herokuapp.com'
TOKEN = None


# None becomes empty string
def xstr(s):
    if s is None:
        return ''
    else:
        return str(s)

def main():
	print
	print "Ringer Shell (version {0})".format(RINGER_SHELL_VERSION)
	print "Type 'help' for command list ('exit' to terminate...)"
	

	choice = "__none__"
	while choice != "exit":
		try:
			choice = raw_input("ringer> ")	
			if choice == "exit": break
			dispatchOp(choice)
		except:
			traceback.print_exc()
			print "Runtime error, please try again..."
	
	print "Good bye!"
	print
	
	sys.exit(0)


def printHelp():
	print "Available commands: {0}".format(sorted(ops.keys()))
	print

		 
def dispatchOp(choice):
	global ops

	if choice not in ops.keys():
		print
		print "Error: Command '{0}' not allowed. Try again.".format(choice)
		print
		return
	else:
		ops[choice] ()
	
def login():
	global TOKEN
	if TOKEN:
		print "Already logged in with token: " + TOKEN
	else:
		username = raw_input("Insert your username: ")
		password = getpass.getpass("Insert password: ")

		conn = http.HTTPConnection(RINGER_API_HOST)
		#conn.request('GET', '/api/v1/auth/token?', body) 
		conn.request('GET', '/api/v1/auth/token?username={0}&password={1}'.format(username, password)) 
		resp = conn.getresponse()
		content = resp.read()
		data = json.loads(content)
		TOKEN = data["token"]
		print "Status: " + xstr(resp.status)
		print "Token: " + TOKEN

def logout():
	global TOKEN
	if TOKEN:
		conn = http.HTTPConnection(RINGER_API_HOST)
		conn.request('GET', '/api/v1/secure/auth/invalidateToken?token={0}'.format(TOKEN)) 
		resp = conn.getresponse()
		content = resp.read()
		TOKEN = None
		print "HTTP Response " + xstr(resp.status)
	else:
		print "Do login and get a token first"
		return
		

def registerNewUser():
	headers = { "Content-Type" : "application/json" }
	req = {}
	print "You're going to register a new user and login with its credentials:"
	req["username"] = raw_input("Insert new user username: ")
	req["password"] = getpass.getpass("Insert new user password: ")
	req_body = json.dumps(req)
	print "HTTP Request: " + req_body
	conn = http.HTTPConnection(RINGER_API_HOST)
	conn.request('POST', '/api/v1/user/register', req_body, headers) 
	resp = conn.getresponse()
	content = resp.read()
	print "HTTP Response " + xstr(resp.status) + ": " + content
	data = json.loads(content)
	TOKEN = data["token"]


def unregister():
	print "UNREG"

def ringOut():
	print "RING"

def listRings():
	print "LIST"

def searchRings():
	print "SEARCH"

def follow():
	print "FOLLOW"

def unfollow():
	print "UNFOLLOW"

def listFollowers():
	print "UNFOLLOW"

def listFollowing():
	print "UNFOLLOW"

def changeServer():
	print "Change server"

def help():
	print "helo"

ops = { 
				"login": login,
				"logout": logout,
				"register": registerNewUser,
				"unregister": unregister,
				"ringOut": ringOut,
				"list rings": listRings,
				"search": searchRings,
				"follow": follow,
				"unfollow": unfollow,
				"list followers": listFollowers,
				"list following": listFollowing,
				"changeServer": changeServer,
				"help": printHelp
			 }


if __name__ == '__main__':
    main()


