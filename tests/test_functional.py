from django.test import LiveServerTestCase
from selenium import webdriver
from selenium.webdriver.common.keys import Keys


class NewVisitorTest(LiveServerTestCase):
	def setUp(self):
		self.browser = webdriver.Firefox()
	
	def tearDown(self):
		self.browser.quit()

	def test_user_goes_loan_calculator_page(self):
		#see we are going to the app4apps site
		self.browser.get(self.live_server_url+"/loancalc")
		header_text = self.browser.find_element_by_tag_name('h1').text
		self.assertIn("Loan Calculator", header_text)

		#we want to press the button and get a new one each time.

