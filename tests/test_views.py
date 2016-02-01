from django.test import TestCase
from django.core.urlresolvers import resolve
from loan_calculator.views import loancalc_page

from django.http import HttpRequest




class LoanCalcTest(TestCase):
	def test_loancalc_url_resolves_to_loancalc_view(self):
		found = resolve('/loancalc/')
		self.assertEqual(found.func, loancalc_page)



	def test_home_page_returns_correct_html(self):
		request = HttpRequest()
		response = loancalc_page(request)

		self.assertTrue(response.content.strip().startswith(b'<!DOCTYPE html>'),
			"Actual response = %s"%response.content.strip())
		self.assertIn(b'The Loan Calculator', response.content)
		self.assertTrue(response.content.strip().endswith(b'</html>'), 
			"Actual response = %s"%response.content)




