POST https://www.googleapis.com/compute/v1/projects/qwiklabs-gcp-c756446486eac65f/global/networks
{
  "routingConfig": {
    "routingMode": "REGIONAL"
  },
  "name": "learnauto",
  "description": "Learn about auto-mode networks",
  "autoCreateSubnetworks": true
}


gcloud compute --project=qwiklabs-gcp-c756446486eac65f networks create learnauto --description="Learn about auto-mode networks" --subnet-mode=auto

gcloud compute --project=qwiklabs-gcp-c756446486eac65f firewall-rules create learnauto-allow-icmp --description="Allows ICMP connections from any source to any instance on the network." --direction=INGRESS --priority=65534 --network=learnauto --action=ALLOW --rules=icmp --source-ranges=0.0.0.0/0

gcloud compute --project=qwiklabs-gcp-c756446486eac65f firewall-rules create learnauto-allow-internal --description="Allows connections from any source in the network IP range to any instance on the network using all protocols." --direction=INGRESS --priority=65534 --network=learnauto --action=ALLOW --rules=all --source-ranges=10.128.0.0/9

gcloud compute --project=qwiklabs-gcp-c756446486eac65f firewall-rules create learnauto-allow-rdp --description="Allows RDP connections from any source to any instance on the network using port 3389." --direction=INGRESS --priority=65534 --network=learnauto --action=ALLOW --rules=tcp:3389 --source-ranges=0.0.0.0/0

gcloud compute --project=qwiklabs-gcp-c756446486eac65f firewall-rules create learnauto-allow-ssh --description="Allows TCP connections from any source to any instance on the network using port 22." --direction=INGRESS --priority=65534 --network=learnauto --action=ALLOW --rules=tcp:22 --source-ranges=0.0.0.0/0



#!/bin/bash
screen -r mcs -X stuff '/save-all\n/save-off\n'
/usr/bin/gsutil cp -R ${BASH_SOURCE%/*}/world gs://${YOUR_BUCKET_NAME}-minecraft-backup/$(date "+%Y%m%d-%H%M%S")-world
screen -r mcs -X stuff '/save-on\n'