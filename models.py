from django.db import models


class Portfolio(models.Model):
	title = models.TextField(null=True, blank=True)
	pass


class Loan(models.Model):
	portfolio = models.ForeignKey(Portfolio, default=None, null=True)
	balance = models.FloatField()
	interest_rate = models.FloatField()
	minimum_payment = models.FloatField()






