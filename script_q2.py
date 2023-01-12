# -*- coding: utf-8 -*-
"""
Created on Wed Dec 21 16:08:35 2022

@author: VChintapalli
"""


  
import requests 
import mysql.connector as mysql
import json

mydb = mysql.connect(
host="localhost",
user="root",
password="Radiology2018",
database="programming_assignment_db",
auth_plugin = "mysql_native_password"
)
  
usersurl = 'https://jsonplaceholder.typicode.com/users'
postsurl = 'https://jsonplaceholder.typicode.com/posts' 
usersResponse = requests.get(usersurl).json()
postsResponse = requests.get(postsurl).json()       # To execute get request 
        

mycursor = mydb.cursor()
mycursor.execute("SELECT * FROM users")

usersResult = mycursor.fetchall()

for x in usersResult:
    for i in range(0, 10):
        if x[3] == [usersResponse][0][i]['email']:
            userId = [usersResponse][0][i]['id']
            username = [usersResponse][0][i]['username']
            lat = [usersResponse][0][i]['address']['geo']['lat']
            long = [usersResponse][0][i]['address']['geo']['lng']
            em = x[3]
            values = (userId,username, lat, long, em)
            sql = "UPDATE users SET id= %s,username = %s, address__geo__lat = %s, address__geo__lng = %s where email = %s"
            mycursor.execute(sql,values)
            mydb.commit()

mycursor = mydb.cursor()
mycursor.execute("SELECT * FROM  posts")
postsResult = mycursor.fetchall()


for x in postsResult:
    for i in range(0,100):
        if x[1] == int(postsResponse[i]['id']):
            recId = x[1]
            usrId = int(postsResponse[i]['userId'])
            postvalues = (usrId,recId)
            sql1 = "UPDATE posts SET userId = %s where id = %s"
            mycursor.execute(sql1,postvalues)
            mydb.commit()
