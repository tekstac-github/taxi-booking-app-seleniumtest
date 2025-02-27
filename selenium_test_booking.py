from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time
import os

# Setup WebDriver with headless options
chrome_options = Options()
chrome_options.add_argument("--headless")
chrome_options.add_argument("--no-sandbox")
chrome_options.add_argument("--disable-dev-shm-usage")
chrome_options.add_argument("--remote-debugging-port=9222")
chrome_options.add_argument(f"--user-data-dir=/tmp/selenium_{os.getenv('BUILD_NUMBER', 'default')}")

# Make sure the right driver is installed (like chromedriver)
driver = webdriver.Chrome(options=chrome_options)

try:
    # Open the taxi booking app
    driver.get("http://localhost:9090/taxiapp/book")

    # Wait for the pickup input to be available
    pickup_input = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.NAME, "pickup"))
    )
    pickup_input.send_keys("Downtown")

    # Wait for the destination input to be available
    destination_input = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.NAME, "destination"))
    )
    destination_input.send_keys("Airport")

    # Wait for the submit button and click it
    submit_button = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "//button[text()='Book Ride']"))
    )
    submit_button.click()

    # Optional: wait for response
    time.sleep(2)

    # Verify submission (adjust selector based on app behavior)
    confirmation_message = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.ID, "confirmation"))
    )
    assert "Booking confirmed" in confirmation_message.text

    print("Test passed: Ride successfully booked")

except Exception as e:
    print(f"Test failed: {e}")

finally:
    driver.quit()
