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
	print "Current server: " + RINGER_API_HOST
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
		conn.request('GET', '/api/v1/auth/token?username={0}&password={1}'.format(username, password)) 
		resp = conn.getresponse()
		content = resp.read()
		print "HTTP Response " + xstr(resp.status) + ": " + content
		data = json.loads(content)
		TOKEN = data["token"]

def logout():
	global TOKEN
	if TOKEN:
		conn = http.HTTPConnection(RINGER_API_HOST)
		conn.request('GET', '/api/v1/secure/auth/invalidateToken?token={0}'.format(TOKEN)) 
		resp = conn.getresponse()
		print "HTTP Response " + xstr(resp.status)
		content = resp.read()
		TOKEN = None
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
	global TOKEN
	if TOKEN:
		conn = http.HTTPConnection(RINGER_API_HOST)
		conn.request('DELETE', '/api/v1/secure/user/delete?token={0}'.format(TOKEN)) 
		resp = conn.getresponse()
		content = resp.read()
		print "HTTP Response " + xstr(resp.status) + ": " + content
		data = json.loads(content)
		TOKEN = None
	else:
		print "Do login and get a token first"
		return

def ringOut():
	if TOKEN:
		headers = { "Content-Type" : "application/json" }
		req = {}
		req["content"] = raw_input("Insert your ring content: ")
		req_body = json.dumps(req)
		conn = http.HTTPConnection(RINGER_API_HOST)
		conn.request('POST', '/api/v1/secure/rings/create?token={0}'.format(TOKEN), req_body, headers) 
		resp = conn.getresponse()
		content = resp.read()
		print "HTTP Response " + xstr(resp.status) + ": " + content
	else:
		print "Do login and get a token first"
		return

def listRings():
	if TOKEN:
		perPage = raw_input("Insert number of rings you want to see: ")
		page = raw_input("Insert page you want to see: ")
		keyword = raw_input("Insert keyword to look for (optional): ")
		conn = http.HTTPConnection(RINGER_API_HOST)
		conn.request('GET', '/api/v1/secure/rings/list?token={0}&page={1}&perPage={2}&keyword={3}'.format(TOKEN, page, perPage, keyword)) 
		resp = conn.getresponse()
		content = resp.read()
		print "HTTP Response " + xstr(resp.status) + ": " + content
		print
		data = json.loads(content)
		for ring in data["rings"]:
			print 'User with id {0} at {1} ringed out: "{2}"'.format(ring["userId"], ring["timestamp"], ring["content"])
	else:
		print "Do login and get a token first"
		return

def follow():
	if TOKEN:
		userIdToFollow = raw_input("Insert the ID of the user to follow: ")
		conn = http.HTTPConnection(RINGER_API_HOST)
		conn.request('POST', '/api/v1/secure/relations/following/add/{0}?token={1}'.format(userIdToFollow, TOKEN)) 
		resp = conn.getresponse()
		content = resp.read()
		print "HTTP Response " + xstr(resp.status) + ": " + content
	else:
		print "Do login and get a token first"
		return

def unfollow():
	if TOKEN:
		userIdToFollow = raw_input("Insert the ID of the user to unfollow: ")
		conn = http.HTTPConnection(RINGER_API_HOST)
		conn.request('POST', '/api/v1/secure/relations/following/remove/{0}?token={1}'.format(userIdToFollow, TOKEN)) 
		resp = conn.getresponse()
		content = resp.read()
		print "HTTP Response " + xstr(resp.status) + ": " + content
	else:
		print "Do login and get a token first"
		return

def showUser():
	if TOKEN:
		username = raw_input("Insert the username of the user to show: ")
		conn = http.HTTPConnection(RINGER_API_HOST)
		conn.request('GET', '/api/v1/secure/user/show/{0}?token={1}'.format(username, TOKEN)) 
		resp = conn.getresponse()
		content = resp.read()
		print "HTTP Response " + xstr(resp.status) + ": " + content
	else:
		print "Do login and get a token first"
		return

def listFollowers():
	if TOKEN:
		perPage = raw_input("Insert number of follower you want to see: ")
		page = raw_input("Insert page you want to see: ")
		conn = http.HTTPConnection(RINGER_API_HOST)
		conn.request('GET', '/api/v1/secure/relations/followers/list?token={0}&page={1}&perPage={2}'.format(TOKEN, page, perPage)) 
		resp = conn.getresponse()
		content = resp.read()
		print "HTTP Response " + xstr(resp.status) + ": " + content
		print
		data = json.loads(content)
		for user in data["users"]:
			print '{0} (ID = {1})'.format(user["username"], user["id"])
	else:
		print "Do login and get a token first"
		return

def listFollowing():
	if TOKEN:
		perPage = raw_input("Insert number of following you want to see: ")
		page = raw_input("Insert page you want to see: ")
		conn = http.HTTPConnection(RINGER_API_HOST)
		conn.request('GET', '/api/v1/secure/relations/following/list?token={0}&page={1}&perPage={2}'.format(TOKEN, page, perPage)) 
		resp = conn.getresponse()
		content = resp.read()
		print "HTTP Response " + xstr(resp.status) + ": " + content
		print
		data = json.loads(content)
		for user in data["users"]:
			print '{0} (ID = {1})'.format(user["username"], user["id"])
	else:
		print "Do login and get a token first"
		return

def changeServer():
	global RINGER_API_HOST
	RINGER_API_HOST = raw_input("Insert new server host: ")
	print "Server changed, current server: " + RINGER_API_HOST

ops = { 
				"login": login,
				"logout": logout,
				"register": registerNewUser,
				"unregister": unregister,
				"new ring": ringOut,
				"show user": showUser,
				"list rings": listRings,
				"follow": follow,
				"unfollow": unfollow,
				"list followers": listFollowers,
				"list following": listFollowing,
				"change server": changeServer,
				"help": printHelp
			 }


if __name__ == '__main__':
    main()


