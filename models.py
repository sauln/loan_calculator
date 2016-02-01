from django.db import models
from django.forms import ModelForm

#create model for loan and user



#by yagni, removing stuff that I don't need yet
#class User(models.Model):
#	name = models.CharField(max_length=120, default="No name")


class Loan(models.Model):
#	user = models.ForeignKey(User, on_delete=models.CASCADE)
	balance = models.DecimalField(max_digits=25, decimal_places=2)
	interest_rate = models.DecimalField(max_digits=10, decimal_places=6)
	minimum_payment = models.DecimalField(max_digits=5, decimal_places=2)

#class LoanForm(ModelForm):
#	class Meta:
#		model = Loan
#		fields = ['balance', 'interest_rate', 'minimum_payment']

