import json
from datetime import datetime
import random
import requests
import time

URL = "http://172.31.253.175:9004/epi-sport/api/sensor/hr"

emailList = ["duff.downey@gmail.com","rosanne.de_michetti@gmail.com","arnaldo.barrington@gmail.com","paddie.steers@gmail.com","saedella.lamacraft@gmail.com","risa.theseira@gmail.com","antonio.lumber@gmail.com","duane.mariet@gmail.com",
             "charisse.feartherby@gmail.com","natale.jakoubek@gmail.com","levin.gregol@gmail.com","laverne.gabites@gmail.com","teodoro.baffin@gmail.com","maure.gilpin@gmail.com",
             "marie-ann.funnell@gmail.com","tuner.waitland@gmail.com","reube.delooze@gmail.com","shara.denisyev@gmail.com","leah.sedgeman@gmail.com","angelo.haggeth@gmail.com",
             "caresa.tottman@gmail.com","andriana.coolbear@gmail.com","kit.henken@gmail.com","andras.durning@gmail.com","osborne.janak@gmail.com","ange.losbie@gmail.com","christen.kytter@gmail.com"]


n = 60
while(n>0):
    for email in emailList:
        HeartRateMeasurement = {"userEmail":email,"heartRate":random.randint(150,250),"time":datetime.now().strftime("%Y-%m-%dT%H:%M:%S")}
        r = requests.post(url=URL, data=json.dumps(HeartRateMeasurement, separators=(',', ':')),headers={'Content-Type': 'application/json'})
        print(json.dumps(HeartRateMeasurement, separators=(',', ':')))
    time.sleep(10)
    n-=1
