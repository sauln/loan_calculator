from django.test import TestCase
from django.core.urlresolvers import resolve

from loan_calculator.views import loancalc_page

class LoanCalcTest(TestCase):
	def test_loancalc_url_resolves_to_loancalc_view(self):
		found = resolve('/loancalc/')
		self.assertEqual(found.func, loancalc_page)


