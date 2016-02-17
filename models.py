from django.db import models


class Portfolio(models.Model):
	title = models.TextField()
	pass


class Loan(models.Model):
	portfolio = models.ForeignKey(Portfolio, default=None)
	balance = models.FloatField()
	interest_rate = models.FloatField()
	minimum_payment = models.FloatField()






