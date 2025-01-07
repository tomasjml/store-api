package com.dialexa.storeapi.unit;

import com.dialexa.storeapi.entities.InvoiceEntity;
import com.dialexa.storeapi.entities.records.InvoiceRecord;
import com.dialexa.storeapi.repositories.InvoiceRepository;
import com.dialexa.storeapi.services.InvoiceService;
import com.dialexa.storeapi.services.ProductService;
import com.dialexa.storeapi.utils.MockUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InvoiceServiceTests {

    @Mock
    private InvoiceRepository mockInvoiceRepository;

    @Mock
    private ProductService mockProductService;

    private InvoiceService invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        invoiceService = new InvoiceService(mockInvoiceRepository, mockProductService);
    }

    @Test
    void findAllInvoices_200() {
        UUID invoiceId = UUID.randomUUID();
        InvoiceEntity invoiceEntity = MockUtil.getInvoiceEntity(invoiceId);
        InvoiceRecord invoiceRecord = MockUtil.getInvoiceRecord(invoiceId);
        List<InvoiceEntity> invoiceEntities = Collections.singletonList(invoiceEntity);
        List<InvoiceRecord> expected = Collections.singletonList(invoiceRecord);
        when(mockInvoiceRepository.findAll()).thenReturn(invoiceEntities);

        List<InvoiceRecord> actual = invoiceService.findAll();

        assertEquals(expected, actual);
        verify(mockInvoiceRepository, times(1)).findAll();
    }

    @Test
    void findInvoiceById_200() {
        UUID invoiceId = UUID.randomUUID();
        InvoiceEntity invoiceEntity = MockUtil.getInvoiceEntity(invoiceId);
        InvoiceRecord invoiceRecord = MockUtil.getInvoiceRecord(invoiceId);
        when(mockInvoiceRepository.findById(invoiceId)).thenReturn(Optional.ofNullable(invoiceEntity));

        InvoiceRecord actual = invoiceService.findById(invoiceId);

        assertEquals(invoiceRecord, actual);
        verify(mockInvoiceRepository, times(1)).findById(invoiceId);
    }

    @Test
    void saveInvoice_200() {
        UUID invoiceId = UUID.randomUUID();
        InvoiceEntity invoiceEntity = MockUtil.getInvoiceEntity(invoiceId);
        when(mockInvoiceRepository.save(any())).thenReturn(invoiceEntity);

        InvoiceEntity actual = invoiceService.save(invoiceEntity);

        assertEquals(invoiceEntity, actual);
        verify(mockInvoiceRepository, times(1)).save(invoiceEntity);
    }

    @Test
    void deleteInvoiceById_200() {
        UUID invoiceId = UUID.randomUUID();
        doNothing().when(mockInvoiceRepository).deleteById(invoiceId);

        invoiceService.deleteById(invoiceId);

        verify(mockInvoiceRepository, times(1)).deleteById(invoiceId);
    }
}