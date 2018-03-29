import json
from itertools import groupby

data = json.load(open('10.json','r+'))


RUID = [item["RUID"] for item in data if "RUID" in item]
CurrentBusStop = [item["CurrentBusStop"] for item in data if "CurrentBusStop" in item]
DestinationBusStop = [item["DestinationBusStop"] for item in data if "DestinationBusStop" in item]
Time = [item["Time"] for item in data if "Time" in item]

RUID_Sorted = sorted(RUID)

personal_requests = len(RUID_Sorted)

# Change string to int
for a in range(personal_requests):
    RUID_Sorted[a] = int(RUID_Sorted[a])

# find duplicates

list_counter = [len(list(group)) for key, group in groupby(RUID_Sorted)]

c = 0

array = []
for i, j in enumerate(list_counter):
    if j > 9:
        array.append(i)

location = []

for i in range(len(array)):
    a = sum(list_counter[:array[i]])
    location.append(a)

for i in range(len(location)):
    del data[location[i - 1]: location[i - 1] + list_counter[array[i]]]

print list_counter
print array
print location
print data


with open('10.json', 'w') as outfile:
    json.dump(data, outfile)