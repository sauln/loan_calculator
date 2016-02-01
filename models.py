from django.db import models


#create model for loan and user

class User(models.Model):
	name = models.CharField(max_length=120, default="No name")


class Loan(models.Model):
	user = models.ForeignKey(User, on_delete=models.CASCADE)
	balance = models.DecimalField(max_digits=25, decimal_places=2)
	interest_rate = models.DecimalField(max_digits=10, decimal_places=6)
	minimum_payment = models.DecimalField(max_digits=5, decimal_places=2)

