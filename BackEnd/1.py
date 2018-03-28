import json
import matplotlib.pyplot as plt

data = json.load(open('10000Student.json'))

RUID = [item["RUID"] for item in data if "RUID" in item]
CurrentBusStop = [item["CurrentBusStop"]
                  for item in data if "CurrentBusStop" in item]
DestinationBusStop = [item["DestinationBusStop"]
                      for item in data if "DestinationBusStop" in item]
Time = [item["Time"] for item in data if "Time" in item]

Time_Sorted = sorted(Time)

Time_Sorted_Split = [i.split(':') for i in Time_Sorted]

total_requests = len(Time_Sorted)

# transfer string into number lists
for a in range(total_requests):
    for b in range(2):
        Time_Sorted_Split[a][b] = int(Time_Sorted_Split[a][b])

y = []
x = 0
z = 0
for i in range(23):

    while Time_Sorted_Split[x][0] <= i:
        x = x + 1

    y.append(x - z)

    while Time_Sorted_Split[z][0] <= i:
        z = z + 1

y.append(len(Time_Sorted) - x)


A = 0
B = []
for index in range(len(y)):
    if y[index] < 0.3 * 840:
        A = 30
    if 0.3 * 840 <= y[index] < 0.4 * 840:
        A = 15
    if 0.4 * 840 <= y[index] < 0.5 * 840:
        A = 10
    if 0.5 * 840 <= y[index] < 0.6 * 840:
        A = 9
    if 0.6 * 840 <= y[index] < 0.7 * 840:
        A = 8
    if 0.7 * 840 <= y[index] < 0.8 * 840:
        A = 7
    if 0.8 * 840 <= y[index] < 0.9 * 840:
        A = 6
    if 0.9 * 840 <= y[index]:
        A = 5
    B.append(A)

    flag = index +1
    print "Time hour from:", index,': 00',' to ',flag,': 00'
    # print "Total Requests in this Hour is : ",y[index]
    print "Arranged bus dispatch rate : ", 'Every',B[index], 'minutes' "\n"


C = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
     13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23]

plt.bar(C, B, alpha=0.9, width=1, facecolor='lightskyblue',
        edgecolor='white', label='one', lw=1)

plt.savefig('Requests_Of_2500Students.png')

plt.show()
