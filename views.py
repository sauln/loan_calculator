from django.forms import modelformset_factory
from django.shortcuts import render
from loan_calculator.models import Loan


def loancalc_page(request):


	LoanFormSet = modelformset_factory(Loan, 
		fields=('balance', 'interest_rate', 'minimum_payment'))
	
	if request.method == "POST":
		formset = LoanFormSet(request.POST, request.FILES, 
			queryset=Loan.objects.all()) 
		if formset.is_valid():
			formset.save()
	else:
		formset = LoanFormSet(queryset=Loan.objects.all()) 
	return render(request, 'loancalc.html', {'formset': formset})
