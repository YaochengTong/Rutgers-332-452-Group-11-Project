# import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
import pandas as pd

full_data = pd.read_csv("ml1000student.csv")

model = LinearRegression()
model.fit(full_data[['RUID', 'Month', 'Day', 'Hour', 'Minutes']], full_data["FeedBack"])
print model.predict([[152007017, 11, 8, 4, 2]])
