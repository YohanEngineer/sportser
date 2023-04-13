import json
from datetime import datetime
import random
import requests
import time

URL = "http://localhost:9004/epi-sport/api/sensor/hr"

emailList = [
    "chastity.ivanyuskin@gmail.com",
    "bettina.bosch@gmail.com",
    "duff.downey@gmail.com",
    "rosanne.de_michetti@gmail.com",
    "arnaldo.barrington@gmail.com",
    "paddie.steers@gmail.com",
    "saedella.lamacraft@gmail.com",
    "risa.theseira@gmail.com",
    "weidar.voysey@gmail.com",
    "antonio.lumber@gmail.com",
    "duane.mariet@gmail.com",
    "wolfie.halwood@gmail.com",
    "charisse.feartherby@gmail.com",
    "natale.jakoubek@gmail.com",
    "levin.gregol@gmail.com",
    "laverne.gabites@gmail.com",
    "lea.kardos@gmail.com",
    "teodoro.baffin@gmail.com",
    "maure.gilpin@gmail.com",
    "marie-ann.funnell@gmail.com",
    "dilan.hurburt@gmail.com",
    "tuner.waitland@gmail.com",
    "reube.delooze@gmail.com",
    "shara.denisyev@gmail.com",
    "claudia.vasilischev@gmail.com",
    "leah.sedgeman@gmail.com",
    "angelo.haggeth@gmail.com",
    "gretna.gaynsford@gmail.com",
    "caresa.tottman@gmail.com"
]


n = 60
while(n>0):
    for email in emailList:
        current_time=datetime.now().strftime("%Y-%m-%dT%H:%M:%S")
        dt = datetime.strptime(current_time, "%Y-%m-%dT%H:%M:%S")
        timestamp = int(dt.timestamp() * 1000)
        HeartRateMeasurement = {"userEmail":email,"heartRate":random.randint(150,250),"time":current_time}
        r = requests.post(url=URL, data=json.dumps(HeartRateMeasurement, separators=(',', ':')),headers={'Content-Type': 'application/json'})
        HeartRateMeasurement = {"userEmail":email,"heartRate":random.randint(150,250),"time":current_time, "timestamp":timestamp}
        print(json.dumps(HeartRateMeasurement, separators=(',', ':')))
    time.sleep(20)
    n-=1


