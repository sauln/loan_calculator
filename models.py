from django.db import models

class Loan(models.Model):
	balance = models.FloatField()
	interest_rate = models.FloatField()
	minimum_payment = models.FloatField()






